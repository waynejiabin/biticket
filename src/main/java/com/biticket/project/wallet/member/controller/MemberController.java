package com.biticket.project.wallet.member.controller;

import com.biticket.common.constant.ConfigConstants;
import com.biticket.common.constant.UserConstants;
import com.biticket.common.enums.KingoTransTypeEnum;
import com.biticket.common.enums.KingoTypeEnum;
import com.biticket.common.enums.SmsTypeEnum;
import com.biticket.common.utils.MajorKeyUtil;
import com.biticket.common.utils.security.JwtTokenUtils;
import com.biticket.framework.web.controller.BaseController;
import com.biticket.framework.web.domain.AjaxResult;
import com.biticket.project.system.config.service.IConfigService;
import com.biticket.project.wallet.member.domain.Member;
import com.biticket.project.wallet.member.service.IMemberService;
import com.biticket.project.wallet.smsValidate.domain.SmsValidate;
import com.biticket.project.wallet.smsValidate.service.ISmsValidateService;
import com.biticket.project.wallet.userTransSerial.domain.UserTransSerial;
import com.biticket.project.wallet.userTransSerial.service.IUserTransSerialService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * 会员 信息操作处理
 *
 * @author biticket
 * @date 2018-08-25
 */
@Controller
@RequestMapping("/wallet/member")
public class MemberController extends BaseController {

    @Autowired
    private IMemberService memberService;

    @Autowired
    private IUserTransSerialService userTransSerialService;

    @Autowired
    ISmsValidateService smsValidateService;

    @Autowired
    IConfigService configService;

    @ApiOperation("验证对象是否存在")
    @PostMapping("/checkIsExist")
    @ResponseBody
    public AjaxResult checkIsExist(String username) {
        boolean isExitsLoginName = memberService.isExitsLoginName(username);
        if (isExitsLoginName) {
            return error("对象不存在！");
        }
        return toAjax(1);
    }

    @ApiOperation(value = "用户数据")
    @PostMapping("/info")
    @ResponseBody
    public AjaxResult memberInfo(String username, String auth) {

        String token = auth.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        String userName = JwtTokenUtils.getUsername(token);
        //System.out.println("userName:"+userName);
        if (!userName.equals(username)) {
            return error("非法请求");
        }
        Member member = memberService.selectMemberByLoginName(userName);
        if (null == member || !UserConstants.NORMAL.equals(member.getMemberStatus())) {
            return AjaxResult.error("非法请求数据！");
        }
        return AjaxResult.success("操作成功！", member);
    }

    /**
     * 查询会员列表
     */
    @ApiOperation(value = "粉丝用户共计")
    @PostMapping("/fansCount")
    @ResponseBody
    public AjaxResult fansCount(String username, String auth) {
        String token = auth.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        String name = JwtTokenUtils.getUsername(token);
        if (!name.equals(username)) {
            return null;
        }
        Member member = memberService.selectMemberByLoginName(username);
        if (null == member && !member.getMemberStatus().equals(UserConstants.NORMAL)) {
            return null;
        }
    /*    if (null == member.getPId()) {
            return null;
        }*/

        startPage();
        int fansCount = memberService.selectMemberCount(member);


        return AjaxResult.success("操作成功!", String.valueOf(fansCount));

    }

    @ApiOperation(value = "查询粉丝用户列表")
    @GetMapping("/fanList")
    @ResponseBody
    public AjaxResult fanList(String username, String auth) {
        String token = auth.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        String name = JwtTokenUtils.getUsername(token);
        if (!name.equals(username)) {
            return null;
        }
        Member member = memberService.selectMemberByLoginName(username);
        if (null == member && !member.getMemberStatus().equals(UserConstants.NORMAL)) {
            return null;
        }
        if (null == member.getPId()) {
            return null;
        }
        Member pMember = new Member();
        pMember.setPId(member.getMemberId());
        startPage();
        List<Member> list = memberService.selectChilderMemberList(pMember);

        return AjaxResult.success("操作成功!", getDataTable(list));

    }

    @ApiOperation(value = "前一日产量")
    @PostMapping("/predayout")
    @ResponseBody
    public AjaxResult preDayOutPut(String username, String auth) {
        String token = auth.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        String name = JwtTokenUtils.getUsername(token);

        if (!name.equals(username)) {
            return error("非法请求");
        }

        Member member = memberService.selectMemberByLoginName(username);
        if (null == member || !member.getMemberStatus().equals(UserConstants.NORMAL)) {
            return AjaxResult.error("非法请求数据！");
        }

        BigDecimal preDayOut = userTransSerialService.selectMemberPreDayOutById(member.getMemberId());
        return AjaxResult.success("操作成功！", preDayOut);
    }

    @ApiOperation(value = "交易列表")
    @GetMapping("/translist")
    @ResponseBody
    public AjaxResult transList(String username, String auth) {
        String token = auth.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        String name = JwtTokenUtils.getUsername(token);
        if (!name.equals(username)) {
            return error("非法请求");
        }

        Member member = memberService.selectMemberByLoginName(username);
        if (null == member || !member.getMemberStatus().equals(UserConstants.NORMAL)) {
            return AjaxResult.error("非法请求数据！");
        }
        UserTransSerial userTransSerial = new UserTransSerial();
        userTransSerial.setUserId(member.getMemberId());
        startPage();
        List<UserTransSerial> list = userTransSerialService.selectUserTransSerialList(userTransSerial);

        return AjaxResult.success("操作成功!", getDataTable(list));
    }

    @ApiOperation(value = "推广列表")
    @GetMapping("/profitlist")
    @ResponseBody
    public AjaxResult profitList(String username, String auth) {
        String token = auth.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        String name = JwtTokenUtils.getUsername(token);

        if (!name.equals(username)) {
            return error("非法请求");
        }

        Member member = memberService.selectMemberByLoginName(username);
        if (null == member || !member.getMemberStatus().equals(UserConstants.NORMAL)) {
            return AjaxResult.error("非法请求数据！");
        }
        UserTransSerial userTransSerial = new UserTransSerial();
        userTransSerial.setUserId(member.getMemberId());
        userTransSerial.setAmountType(KingoTypeEnum.KINGO_RELEASE_ACCELERATED.getType());
        ArrayList status = new ArrayList<>();
        status.add(KingoTypeEnum.KINGO_RELEASE_ACCELERATED.getType());
        status.add(KingoTypeEnum.KINGO_RECOMMEND_IN.getType());
        startPage();
        List<UserTransSerial> list = userTransSerialService.selectUserTransSerialListByStatus(userTransSerial.getUserId(), status);

        return AjaxResult.success("操作成功!", getDataTable(list));
    }

    @ApiOperation(value = "推广奖励统计")
    @PostMapping("/profit")
    @ResponseBody
    public AjaxResult profitTotal(String username, String auth) {
        String token = auth.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        String name = JwtTokenUtils.getUsername(token);

        if (!name.equals(username)) {
            return error("非法请求");
        }

        Member member = memberService.selectMemberByLoginName(username);
        if (null == member || !member.getMemberStatus().equals(UserConstants.NORMAL)) {
            return AjaxResult.error("非法请求数据！");
        }
        UserTransSerial userTransSerial = new UserTransSerial();
        userTransSerial.setUserId(member.getMemberId());
        userTransSerial.setAmountType(KingoTypeEnum.KINGO_RELEASE_ACCELERATED.getType());
        List<Integer> status = new ArrayList<>();
        status.add(KingoTypeEnum.KINGO_RELEASE_ACCELERATED.getType());
        status.add(KingoTypeEnum.KINGO_RECOMMEND_IN.getType());

        BigDecimal profitTotal = userTransSerialService.selectUserTransSerialListByStatusTotal(userTransSerial.getUserId(), status);

        return AjaxResult.success("操作成功!", profitTotal.toString());
    }

    /**
     * 1、判断对象是否存在.
     * 2、判断支付金额是否合法（是否为负数，是否大于现有活动数）
     * 3、判断验证码是否正确
     * 4、改变双方数据（两小时内如果没有异议且不进行确认操作，由系统进行确认）
     * 转账方： 总数不变，将金果放置到锁仓中。由转出方进行交易操作确认。
     * 接收方：总数改变，接收的金果放置锁仓中。
     * 5、插入交易记录
     *
     * @param username
     * @param toUserName
     * @param amount
     * @return
     */
    @ApiOperation("转账操作")
    @PostMapping("/trade")
    @ResponseBody
    @Transactional(rollbackOn = Exception.class)
    public AjaxResult trade(String username, String toUserName, BigDecimal amount, String code) {
        int row = 0;
        /**1、判断对象是否存在.*/
        Member current = memberService.selectMemberByLoginName(username);
        Member toMember = memberService.selectMemberByLoginName(toUserName);
        if (null == current || null == toMember) {
            return AjaxResult.error("对象不存在！");
        }
        /**2、判断支付金额是否合法（是否为负数，是否大于现有活动数）*/
        if (amount.compareTo(BigDecimal.ZERO) <= 0 || amount.compareTo(current.getAmountAvailable()) > 0) {
            return error("转账金额异常！请重试！");
        }
        /** 3、判断验证码是否正确*/
        /**验证码*/
        SmsValidate smsValidate = new SmsValidate();
        smsValidate.setSmsType(SmsTypeEnum.SMS_TRADE.getValue());
        smsValidate.setMobileNumber(username);

        smsValidate = smsValidateService.selectSmsValidateByMobile(smsValidate);
        if (null == smsValidate || !code.equals(smsValidate.getValidateCode())) {
            return error("短信验证码错误！请重新输入！");
        }
        /** 4、改变双方数据（两小时内锁定交易对象,将交易数据放置到preamount中，如果无异议将在两小时内使用定时器规则将数据移动？）*/
        current.setAmountAvailable(current.getAmountAvailable().subtract(amount));
        current.setAmountFrozen(current.getAmountFrozen().add(amount));
        //current.setAmountTotal(current.getAmountTotal().subtract(amount).setScale(4, RoundingMode.HALF_DOWN));
        memberService.updateMember(current);

        /**手续费
         * */
        String obj = configService.selectConfigByKey(ConfigConstants.MEMBER_TRADE_FREE_KEY);
        if ("".equals(obj)) {
            obj = "0";
        }
        BigDecimal freeRate = new BigDecimal(obj);
        BigDecimal freeRateAmount = amount.multiply(freeRate).setScale(4, RoundingMode.HALF_DOWN);
        BigDecimal getAmount = amount.subtract(freeRateAmount);

        toMember.setAmountFrozen(current.getAmountAvailable().add(getAmount));
        toMember.setAmountTotal(current.getAmountTotal().add(getAmount));
        memberService.updateMember(toMember);


        /** 5、插入预交易记录（交易双方）*/
        List<UserTransSerial> transSerialList = new ArrayList<UserTransSerial>();

        /**付款方*/
        UserTransSerial transSerial = new UserTransSerial();
        String tranNo = MajorKeyUtil.createMajorKey("td");
        transSerial.setDealNo(tranNo);
        transSerial.setUserId(current.getMemberId());
        transSerial.setUserName(current.getNickName());
        transSerial.setAmountType(KingoTypeEnum.KINGO_OUT.getType());
        transSerial.setTransStatus(KingoTransTypeEnum.KINGO_TRANS_OUT_CONFIRM.getValue());
        transSerial.setAmountRemark(KingoTypeEnum.KINGO_OUT.getRemark());
        transSerial.setAmount(amount);
        transSerial.setPreAmount(amount);
        transSerial.setUserMobile(current.getMemberMobile());
        transSerialList.add(transSerial);

        /**手续费*/
        transSerial = new UserTransSerial();
        transSerial.setDealNo(tranNo);
        transSerial.setAmountType(KingoTypeEnum.KINGO_IN.getType());
        transSerial.setTransStatus(KingoTransTypeEnum.KINGO_TRANS_RECLAIM_CONFIRM.getValue());
        transSerial.setAmountRemark(KingoTransTypeEnum.KINGO_TRANS_RECLAIM_CONFIRM.getRemark());
        transSerial.setAmount(freeRateAmount);
        transSerial.setPreAmount(freeRateAmount);
        transSerialList.add(transSerial);
        /**收方*/
        transSerial = new UserTransSerial();
        transSerial.setDealNo(tranNo);
        transSerial.setUserId(toMember.getMemberId());
        transSerial.setAmountType(KingoTypeEnum.KINGO_IN.getType());
        transSerial.setTransStatus(KingoTransTypeEnum.KINGO_TRANS_IN_CONFIRM.getValue());
        transSerial.setAmountRemark(KingoTypeEnum.KINGO_IN.getRemark());
        transSerial.setAmount(getAmount);
        transSerial.setPreAmount(getAmount);
        transSerial.setUserMobile(toMember.getMemberMobile());
        transSerial.setUserName(toMember.getNickName());
        transSerialList.add(transSerial);

        userTransSerialService.insertBatch(transSerialList);

        return toAjax(row);
    }

    /***
     * 1、根据交易单号获取当前单据信息
     * 2、验证单据的合法性
     * 3、变更双方数据信息
     * 4、更新单交易状态
     * 5、记录操作记录信息
     * @param username 转出人
     * @param tranNO 交易单号
     * @return
     */
    @ApiOperation("交易手动确认")
    @PostMapping("/tradeConfirm")
    @ResponseBody
    @Transactional(rollbackOn = Exception.class)
    public AjaxResult tradeConfirm(String username, String target, String tranNO) {
        UserTransSerial transSerial = new UserTransSerial();
        transSerial.setDealNo(tranNO);
        List<UserTransSerial> list = new ArrayList<>();
        List<UserTransSerial> transSerialList = userTransSerialService.selectUserTransSerialList(transSerial);
        /**交易是双方的*/
        if (null == transSerialList && transSerialList.size() < 2) {
            return error("无效的操作数据！");
        }

        UserTransSerial toTrans = transSerialList.stream().filter(obj -> obj.getUserName().equals(username)).findAny().get();

        UserTransSerial targetTrans = transSerialList.stream().filter(obj -> obj.getUserName().equals(target)).findAny().get();
        if (!toTrans.getTransStatus().equals(KingoTransTypeEnum.KINGO_TRANS_OUT_CONFIRM) || !targetTrans.getTransStatus().equals(KingoTransTypeEnum.KINGO_TRANS_IN_CONFIRM)) {
            return toAjax(0);
        }
        if (toTrans.getAmount().compareTo(BigDecimal.ZERO) < 0 || targetTrans.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            return toAjax(0);
        }

        List<Member> memberList = new ArrayList<>();
        Member member = new Member();
        member.setMemberId(toTrans.getUserId());
        member.setAmountFrozen(toTrans.getAmount().multiply(new BigDecimal("-1")));
        member.setAmountTotal(toTrans.getAmount().multiply(new BigDecimal("-1")));
        member.setAmountAvailable(new BigDecimal(0));
        memberList.add(member);

        member = new Member();
        member.setAmountFrozen(targetTrans.getAmount().multiply(new BigDecimal("-1")));
        member.setAmountAvailable(targetTrans.getAmount());
        member.setAmountTotal(new BigDecimal(0));
        memberList.add(member);

        memberService.changeMemberAmount(memberList);

        /**记录更新*/
        transSerialList.stream().forEach(trans->{
            if (trans.getTransStatus().equals(KingoTransTypeEnum.KINGO_TRANS_IN_CONFIRM.getValue())) {
                trans.setTransStatus(KingoTransTypeEnum.KINGO_TRANS_IN.getValue());
            } else if (trans.getTransStatus().equals(KingoTransTypeEnum.KINGO_TRANS_OUT_CONFIRM.getValue())) {
                trans.setTransStatus(KingoTransTypeEnum.KINGO_TRANS_OUT.getValue());
            } else {
                trans.setTransStatus(KingoTransTypeEnum.KINGO_TRANS_RECLAIM.getValue());
            }
            list.add(trans);
        });

        userTransSerialService.updateBatchTransStatus(list);
        return toAjax(1);
    }
 /*   @ApiOperation(value = "奖励统计")
    @PostMapping("/reward")
    @ResponseBody
    public AjaxResult rewardTotal(String username, String auth) {
        String token = auth.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        String name = JwtTokenUtils.getUsername(token);

        if (!name.equals(username)) {
            return error("非法请求");
        }

        Member member = memberService.selectMemberByLoginName(username);
        if (null == member || !member.getMemberStatus().equals(UserConstants.NORMAL)) {
            return AjaxResult.error("非法请求数据！");
        }
        UserTransSerial userTransSerial = new UserTransSerial();
        userTransSerial.setUserId(member.getMemberId());
        userTransSerial.setAmountType(KingoTypeEnum.KINGO_RELEASE_ACCELERATED.getType());
        List<Integer> status = new ArrayList<>();
        status.add(KingoTypeEnum.KINGO_RECOMMEND_IN.getType());

        //TODO 这个地方的数据还需要考虑来源
        BigDecimal rewardTotal = new BigDecimal(0.0000);
        if(member.getMemberType()!=MemberTypeEnum.MEMBER_PARTNER.getValue()){
            return  AjaxResult.success("操作成功!", rewardTotal.toString());
        }
         rewardTotal= userTransSerialService.selectUserRewardTotal(userTransSerial.getUserId(),status);

        return AjaxResult.success("操作成功!", rewardTotal.toString());
    }
    @ApiOperation(value = "推广列表")
    @GetMapping("/rewardlist")
    @ResponseBody
    public AjaxResult rewardList(String username, String auth) {
        String token = auth.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        String name = JwtTokenUtils.getUsername(token);

        if (!name.equals(username)) {
            return error("非法请求");
        }

        Member member = memberService.selectMemberByLoginName(username);
        if (null == member || !member.getMemberStatus().equals(UserConstants.NORMAL)) {
            return AjaxResult.error("非法请求数据！");
        }
        UserTransSerial userTransSerial = new UserTransSerial();
        userTransSerial.setUserId(member.getMemberId());
        userTransSerial.setAmountType(KingoTypeEnum.KINGO_RELEASE_ACCELERATED.getType());
        ArrayList status = new ArrayList<>();
        status.add(KingoTypeEnum.KINGO_RELEASE_ACCELERATED.getType());
        status.add(KingoTypeEnum.KINGO_RECOMMEND_IN.getType());
        startPage();
        List<UserTransSerial> list = userTransSerialService.selectUserTransSerialListByStatus(userTransSerial.getUserId(), status);

        return AjaxResult.success("操作成功!", getDataTable(list));
    }
*/
}
