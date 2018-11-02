package com.ruoyi.framework.web.filter;

import com.ruoyi.common.thirdUtil.JsonUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.JwtTokenUtils;
import com.ruoyi.framework.security.PasswordService;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.domain.JwtUser;
import com.ruoyi.project.wallet.member.domain.Member;
import com.ruoyi.project.wallet.member.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 验证用户名密码正确后，生成一个token，并将token返回给客户端
 * 该类继承自UsernamePasswordAuthenticationFilter，重写了其中的2个方法
 * attemptAuthentication ：接收并解析用户凭证。
 * successfulAuthentication ：用户成功登录后，这个方法会被调用，我们在这个方法里生成token。
 *
 * @author zhaoxinguo on 2017/9/12.
 */
public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    private ThreadLocal<Integer> rememberMe = new ThreadLocal<>();
    @Autowired
    IMemberService memberService;
    @Autowired
    PasswordService passwordService;

    private AuthenticationManager authenticationManager;

    public JWTLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        /**默认是/login 可以自定义*/
        super.setFilterProcessesUrl("/login");
    }

    /**
     * 接收并解析用户登陆信息  /login,
     * 为已验证的用户返回一个已填充的身份验证令牌，表示成功的身份验证
     * 返回null，表明身份验证过程仍在进行中。在返回之前，实现应该执行完成该过程所需的任何额外工作。
     * 如果身份验证过程失败，就抛出一个AuthenticationException
     *
     * @param req 从中提取参数并执行身份验证
     * @param res 如果实现必须作为多级身份验证过程的一部分(比如OpenID)进行重定向，则可能需要响应
     * @return 身份验证的用户令牌，如果身份验证不完整，则为null。
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) {
        res.setContentType("text/html;charset=UTF-8");
        try {
            //得到用户登陆信息,并封装到 Authentication 中,供自定义用户组件使用.
            String username = req.getParameter("username");
            String password = req.getParameter("password");

            // 从输入流中获取到登录的信息

            Member member = new Member();
            member.setMemberPassword(password);
            member.setLoginName(username);
            //Member loginUser = new ObjectMapper().readValue(req.getInputStream(), Member.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(member.getLoginName(), member.getMemberPassword(), new ArrayList<>())
            );
        } catch (AuthenticationException e) {
            try {
                res.getWriter().print(JsonUtil.mapToJson(AjaxResult.error("用户或密码错误")));
                res.getWriter().close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
 return  null;
    }

    /**
     * 用户成功登录后，这个方法会被调用，我们在这个方法里生成token
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) {
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("application/json; charset=utf-8");
        AjaxResult jsonObj = new AjaxResult();
        try {
            /** 查看源代码会发现调用getPrincipal()方法会返回一个实现了`UserDetails`接口的对象
             所以就是JwtUser啦**/
            Object obj = authResult.getPrincipal();
            if (null == obj) {
                throw new BadCredentialsException("您输入的信息有误！");
            }
            JwtUser jwtUser = (JwtUser) authResult.getPrincipal();
            String token = JwtTokenUtils.createToken(jwtUser.getUsername(),jwtUser.getId(), false);
            /** 返回创建成功的token
             // 但是这里创建的token只是单纯的token
             // 按照jwt的规定，最后请求的格式应该是 `Bearer token`**/
            response.setHeader("Authorization", JwtTokenUtils.TOKEN_PREFIX + token);

            jsonObj.put("code",0);
            jsonObj.put("msg", "操作成功");
            jsonObj.put("body",jwtUser);
            jsonObj.put("Authorization",JwtTokenUtils.TOKEN_PREFIX + token);
            response.getWriter().write(JsonUtil.mapToJson(jsonObj));
            response.getWriter().flush();

            System.out.println(jsonObj);

        } catch (Exception e) {
            String msg = "用户或密码错误";
            try {
                response.getWriter().write(msg);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(JsonUtil.mapToJson(AjaxResult.error("authentication failed, reason: " + failed.getMessage())));
       // response.getWriter().write("authentication failed, reason: " + failed.getMessage());
        response.getWriter().flush();
    }
}
