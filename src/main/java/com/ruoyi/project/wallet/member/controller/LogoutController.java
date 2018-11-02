package com.ruoyi.project.wallet.member.controller;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.enums.KingoTypeEnum;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.JwtTokenUtils;
import com.ruoyi.framework.security.PasswordService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.filter.JWTLoginFilter;
import com.ruoyi.project.wallet.member.domain.Member;
import com.ruoyi.project.wallet.member.service.IMemberService;
import com.ruoyi.project.wallet.userTransSerial.domain.UserTransSerial;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

@RestController
@Api(value = "登录管理", description = "登录管理")
public class LogoutController extends BaseController {

    @ApiOperation(value = "登出")
    @PostMapping("/logout")
    @ResponseBody
    public AjaxResult logout(String username,String auth)
    {
        String token = auth.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        String name = JwtTokenUtils.getUsername(token);

        if(!name.equals(username)){
            return  AjaxResult.error("非法请求");
        }

        JwtTokenUtils.setTokenInvalid(token);
        return AjaxResult.success();
    }
}
