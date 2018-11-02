package com.biticket.project.wallet.member.controller;

import com.biticket.common.utils.security.JwtTokenUtils;
import com.biticket.framework.web.controller.BaseController;
import com.biticket.framework.web.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
