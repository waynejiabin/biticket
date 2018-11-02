package com.ruoyi.project.wallet.memberOrder.controller;
 
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.enums.KingoTypeEnum;
import com.ruoyi.common.enums.MemberTypeEnum;
import com.ruoyi.common.utils.security.JwtTokenUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.wallet.member.domain.Member;
import com.ruoyi.project.wallet.member.service.IMemberService;
import com.ruoyi.project.wallet.memberOrder.domain.MemberOrder;
import com.ruoyi.project.wallet.memberOrder.service.IMemberOrderService;
import com.ruoyi.project.wallet.userTransSerial.domain.UserTransSerial;
import com.ruoyi.project.wallet.userTransSerial.service.IUserTransSerialService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 会员 信息操作处理
 *
 * @author ruoyi
 * @date 2018-08-25
 */
@Controller
@RequestMapping("/wallet/member")
public class MemberOrderController extends BaseController {

 @Autowired
 private IMemberService memberService;
    @Autowired
 private IMemberOrderService memberOrderService;

    @ApiOperation(value = "奖励统计")
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
         rewardTotal= memberOrderService.selectUserRewardTotal(member.getMemberId());

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

        MemberOrder order = new MemberOrder();
        order.setMemberId(member.getMemberId());
        startPage();
        List<MemberOrder> list = memberOrderService.selectMemberOrderList(order);

        return AjaxResult.success("操作成功!", getDataTable(list));
    }

}
