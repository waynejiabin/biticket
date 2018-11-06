package com.biticket.project.wallet.member.controller;


import com.biticket.common.enums.SmsTypeEnum;
import com.biticket.common.utils.MajorKeyUtil;
import com.biticket.common.utils.PhoneFormatCheckUtils;
import com.biticket.framework.message.SmsUtils;
import com.biticket.framework.message.dayu.DayuSmsConstants;
import com.biticket.framework.message.yy.YySmsUtil;
import com.biticket.framework.message.yy.common.YySmsConstants;
import com.biticket.framework.web.controller.BaseController;
import com.biticket.framework.web.domain.AjaxResult;
import com.biticket.project.wallet.member.domain.Member;
import com.biticket.project.wallet.member.service.IMemberService;
import com.biticket.project.wallet.smsValidate.domain.SmsValidate;
import com.biticket.project.wallet.smsValidate.service.ISmsValidateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

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
        if (type.equals(String.valueOf(SmsTypeEnum.SMS_REGISTER.getValue())) && isExitsLoginName) {
            return error("当前账号已注册！");
        } else if (!isExitsLoginName && !type.equals(String.valueOf(SmsTypeEnum.SMS_REGISTER.getValue()))) {
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
            /**注册*/
            case "0":
                //context = YySmsConstants.TEMPALTE_REGISTER.replace("@", code);
                context = DayuSmsConstants.Tempalte_register_cdoe;
                break;
            /**找回密码*/
            case "1":
               // context = YySmsConstants.TEMPALTE_FIND_PWD.replace("@", code);
                context =DayuSmsConstants.tempalte_findPWD_cdoe;
                break;
            /**交易*/
            case "2":
                //context = YySmsConstants.TEMPALTE_TRADE.replace("@", code);
                context = DayuSmsConstants.tempalte_biticket_trade;
                break;
            default:
                //context = YySmsConstants.TEMPALTE_REGISTER.replace("@", code);
                context = DayuSmsConstants.Tempalte_register_cdoe;
                break;
        }


        Map map = new HashMap<>();
        String[] mobiles = new String[1];
        mobiles[0] = mobile;
        map.put("code",code);

        /**发送短信*/
        //boolean smsFlag = YySmsUtil.sendSms(mobile, context);
        boolean smsFlag  =  SmsUtils.sendSms(map,context,mobiles);
        if (smsFlag) {
            /**插入发送记录*/
            smsValidate.setMobileNumber(mobile);
            smsValidate.setSmsContent(context);
            smsValidate.setValidateCode(String.valueOf(code));
            smsValidate.setSmsType(Integer.valueOf(type));
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
