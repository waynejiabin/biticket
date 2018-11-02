package com.biticket.project.wallet.member.controller;

import com.biticket.framework.web.controller.BaseController;
import com.biticket.framework.web.domain.AjaxResult;
import com.biticket.project.wallet.userapp.domain.UserApp;
import com.biticket.project.wallet.userapp.service.IUserAppService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/member")
@Api(value = "会员公共方法", description = "不需要权限管理的接口")
public class MemberCommonController extends BaseController
{

    @Autowired
    private IUserAppService userAppService;


    /**
     * 获取最新手机app版本
     */
    @ApiOperation("获取手机APP最新版本")
    @PostMapping("/getLasterRelease")
    @ResponseBody
    public AjaxResult getLasterRelease(Integer appType, Integer apkType)
    {
        ModelMap mmap=new ModelMap();
        UserApp userApp = userAppService.selectUserAppLasterRelease(apkType,appType);
        mmap.put("userapp", userApp);
        return  AjaxResult.success("操作成功！",mmap);

    }
}
