package com.ruoyi.framework.web.filter;


import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.ShiroConstants;
import com.ruoyi.common.exception.TokenException;
import com.ruoyi.common.exception.UsernameIsExitedException;
import com.ruoyi.common.thirdUtil.JsonUtil;
import com.ruoyi.common.utils.security.JwtTokenUtils;
import com.ruoyi.framework.security.GrantedAuthorityImpl;
import com.ruoyi.framework.web.domain.AjaxResult;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * 自定义JWT认证过滤器
 * 该类继承自BasicAuthenticationFilter，在doFilterInternal方法中，
 * 从http头的Authorization 项读取token数据，然后用Jwts包提供的方法校验token的合法性。
 * 如果校验通过，就认为这是一个取得授权的合法请求
 *
 * @author   2018/9/13.
 */
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    /**
     * 在此方法中检验客户端请求头中的token,
     * 如果存在并合法,就把token中的信息封装到 Authentication 类型的对象中,
     * 最后使用  SecurityContextHolder.getContext().setAuthentication(authentication); 改变或删除当前已经验证的 pricipal
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws  IOException,ServletException {
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("application/json; charset=utf-8");
       // try {
            String header = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
            //判断是否有token 如果请求头中没有Authorization信息则直接放行了
            if (header == null || !header.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
                chain.doFilter(request, response);
                return;
            }
            String userName = request.getParameter(ShiroConstants.CURRENT_USERNAME);
            //如果请求头中有token，则进行解析，并且设置认证信息
            UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(header,userName);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            //放行
            chain.doFilter(request, response);

       /* } catch (ExpiredJwtException e) {
            logger.error("Token已过期: {} " + e);
            logger.error("Token已过期: {} " + new TokenException("Token已过期").getMessage());
            throw new TokenException("Token已过期");

        } catch (UnsupportedJwtException e) {
            logger.error("Token格式错误: {} " + e);
            response.getWriter().write("Token格式错误");
            throw new TokenException("Token格式错误");
        } catch (MalformedJwtException e) {
            logger.error("Token没有被正确构造: {} " + e);
            response.getWriter().write("Token没有被正确构造");
            throw new TokenException("Token没有被正确构造");
        } catch (SignatureException e) {
            logger.error("签名失败: {} " + e);

            response.getWriter().write("签名失败") ;
            throw new TokenException("签名失败");
        } catch (IllegalArgumentException e) {
            logger.error("非法参数异常: {} " + e);

            response.getWriter().write("非法参数异常" );
            throw new TokenException("非法参数异常");
        }*/

        //super.doFilterInternal(request, response, chain);
    }


    /**
     * 解析token中的信息,并判断是否过期
     * 这里从token中获取用户信息并新建一个token
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader, String reqUserName) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {
        //String token = request.getHeader("Authorization");
        if (tokenHeader == null || tokenHeader.isEmpty()) {
            throw new TokenException("Token为空");
        }

        // parse the token.
        //String user = null;
          try {
            /*Claims claims = Jwts.parser().setSigningKey(Constants.SIGNING_KEY)
                    .parseClaimsJws(tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, ""))
                    .getBody();*/
        //得到用户名
        String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        String username = JwtTokenUtils.getUsername(token);
        if(!reqUserName.equals(username))
        {
            throw new TokenException("操作异常！请重新登录！");
        }

        //判断是否过期
        if (JwtTokenUtils.isExpiration(token)) {
            throw new UsernameIsExitedException("该账号已过期,请重新登录!");
        }
        if (username != null) {
            return new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
        }

        } catch (ExpiredJwtException e) {
            logger.error("Token已过期: {} " + e);
            throw new TokenException("Token已过期");
        } catch (UnsupportedJwtException e) {
            logger.error("Token格式错误: {} " + e);
            throw new TokenException("Token格式错误");
        } catch (MalformedJwtException e) {
            logger.error("Token没有被正确构造: {} " + e);
            throw new TokenException("Token没有被正确构造");
        } catch (SignatureException e) {
            logger.error("签名失败: {} " + e);
            throw new TokenException("签名失败");
        } catch (IllegalArgumentException e) {
            logger.error("非法参数异常: {} " + e);
            throw new TokenException("非法参数异常");
        }
        return null;
    }

}
