package com.ruoyi.project.wallet.member.controller;


import com.ruoyi.common.enums.SmsTypeEnum;
import com.ruoyi.common.exception.UsernameIsExitedException;
import com.ruoyi.common.utils.MajorKeyUtil;
import com.ruoyi.common.utils.PhoneFormatCheckUtils;
import com.ruoyi.common.utils.security.EncryptUtils;
import com.ruoyi.framework.message.dayu.SmsUtil;
import com.ruoyi.framework.message.yy.YySmsUtil;
import com.ruoyi.framework.message.yy.common.YySmsConstants;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.filter.JWTLoginFilter;
import com.ruoyi.project.wallet.member.domain.Member;
import com.ruoyi.project.wallet.member.service.IMemberService;
import com.ruoyi.project.wallet.smsValidate.domain.SmsValidate;
import com.ruoyi.project.wallet.smsValidate.service.ISmsValidateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author admin
 */
@RestController
@RequestMapping("/member")
@Api(value = "注册管理", description = "注册管理")
public class RegisterController extends BaseController {

    @Autowired
    IMemberService memberService;

    @Autowired
    ISmsValidateService smsValidateService;

    @ApiOperation(value = "注册用户")
    @PostMapping("/register/{smsCode}")
    @ResponseBody
    public AjaxResult register(@PathVariable("smsCode") String smsCode, String loginName, String memberPassword,
                               String memberPassword2, String parenter) {
        Member member = new Member();
        member.setParenter(parenter);
        member.setLoginName(loginName);
        member.setMemberMobile(loginName);
        member.setMemberPassword(bCryptPasswordEncoder.encode(memberPassword));

        if (null == smsCode) {
            return error("无效验证码");
        }
        if (!memberPassword.equals(memberPassword2)) {
            return error("两次输入的密码不一致！");
        }
        /**用户名*/
        if (memberService.isExitsLoginName(member.getLoginName())) {
            return error("当前账号已经存在，请注册新的账号！");
        }
        /**验证码*/
        SmsValidate smsValidate = new SmsValidate();
        smsValidate.setSmsType(SmsTypeEnum.SMS_REGISTER.getValue());
        smsValidate.setMobileNumber(loginName);

        smsValidate = smsValidateService.selectSmsValidateByMobile(smsValidate);
        if (null == smsValidate) {
            return error("无效验证码！");
        }
        if (smsValidate.getExpireTime().getTime() < System.currentTimeMillis()) {
            return error("验证码已过期，请重新获取！");
        }

        if (!smsValidate.getValidateCode().equals(smsCode)) {
            return error("验证码错误！");
        }

        return toAjax(memberService.insertMember(member));
    }

    /***
     * 验证码复用，一个号码10分钟之内都有效
     * add 增加类型判断，并且增加为注册情况下先判断账号是否存在
     * @param mobile
     * @return
     */
    @ApiOperation(value = "发送验证码")
    @PostMapping("/smsSend")
    @Transactional(rollbackOn = Exception.class)
    @ResponseBody
    public AjaxResult smsSend(String mobile, @RequestParam(defaultValue = "0")String type) throws Exception {
        if (null == mobile || mobile.length() != 11) {
            return error("请输入正确的手机号码!");
        }
        if (!PhoneFormatCheckUtils.isChinaPhoneFormat(mobile)) {
            return error("请输入正确的手机号码！");
        }
        boolean isExitsLoginName = memberService.isExitsLoginName(mobile);
        if (type.equals(SmsTypeEnum.SMS_REGISTER.getValue())) {
            return error("当前账号已注册！");
        } else if (!isExitsLoginName) {
            return error("当前账号不存在！");
        }

        SmsValidate smsValidate = new SmsValidate();
        smsValidate.setSmsType(Integer.valueOf(type));
        smsValidate.setMobileNumber(mobile);

        smsValidate = smsValidateService.selectSmsValidateByMobile(smsValidate);
        if (null != smsValidate) {
            if (smsValidate.getExpireTime().getTime() > System.currentTimeMillis()) {
                return success("短信已发送成功！十分钟内有效！");
            }
        }

        smsValidate = new SmsValidate();
        String code = MajorKeyUtil.createRandomSix();
        String context;
        switch (type) {
            case "0":
                context = YySmsConstants.TEMPALTE_REGISTER.replace("@", code);
                break;
            case "1":
                context = YySmsConstants.TEMPALTE_FIND_PWD.replace("@", code);
                break;
            case "2":
                context = YySmsConstants.TEMPALTE_TRADE.replace("@", code);
                break;
            default:
                context = YySmsConstants.TEMPALTE_REGISTER.replace("@", code);
                break;
        }

        smsValidate.setMobileNumber(mobile);
        smsValidate.setSmsContent(context);
        smsValidate.setValidateCode(String.valueOf(code));
        smsValidate.setSmsType(Integer.valueOf(type));


        /**发送短信*/
        boolean smsFlag = YySmsUtil.sendSms(mobile, context);
        if (smsFlag) {
            /**插入发送记录*/
            int flag = smsValidateService.insertSmsValidate(smsValidate);
            if (flag > 0) {
                return AjaxResult.success("短信发送成功！十分钟内操作有效！");
            } else {
                return AjaxResult.error("短信发送失败！");
            }
        } else {
            return AjaxResult.error("操作失败！");
        }
    }


    @ApiOperation(value = "找回密码")
    @PostMapping("/findPWD/{smsCode}")
    @ResponseBody
    public AjaxResult findPWD(@PathVariable("smsCode") String smsCode, String loginName, String memberPassword,
                              String memberPassword2) {

        if (null == smsCode) {
            return error("无效验证码");
        }
        if (!memberPassword.equals(memberPassword2)) {
            return error("两次输入的密码不一致！");
        }
        /**验证码*/
        SmsValidate smsValidate = new SmsValidate();
        smsValidate.setSmsType(SmsTypeEnum.SMS_FIND_PWD.getValue());
        smsValidate.setMobileNumber(loginName);

        smsValidate = smsValidateService.selectSmsValidateByMobile(smsValidate);
        if (null == smsValidate) {
            return error("无效验证码！");
        }
        if (smsValidate.getExpireTime().getTime() < System.currentTimeMillis()) {
            return error("验证码已过期，请重新获取！");
        }

        if (!smsValidate.getValidateCode().equals(smsCode)) {
            return error("验证码错误！");
        }
        /**判断对象是否存在*/
        Member member1 = memberService.selectMemberByLoginName(loginName);
        if (null == member1) {
            return error("用户不存在！");
        }

        Member member = new Member();
        member.setLoginName(loginName);
        member.setMemberPassword(bCryptPasswordEncoder.encode(memberPassword));
        member.setMemberId(member1.getMemberId());
        return toAjax(memberService.updateMember(member));
    }
}
