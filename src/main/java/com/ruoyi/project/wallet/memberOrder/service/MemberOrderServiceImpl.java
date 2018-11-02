package com.ruoyi.project.wallet.memberOrder.service;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.wallet.memberOrder.mapper.MemberOrderMapper;
import com.ruoyi.project.wallet.memberOrder.domain.MemberOrder;
import com.ruoyi.project.wallet.memberOrder.service.IMemberOrderService;
import com.ruoyi.common.support.Convert;

/**
 * 合伙人单量 服务层实现
 * 
 * @author ruoyi
 * @date 2018-09-08
 */
@Service
public class MemberOrderServiceImpl implements IMemberOrderService 
{
	@Autowired
	private MemberOrderMapper memberOrderMapper;

	/**
     * 查询合伙人单量信息
     * 
     * @param orderId 合伙人单量ID
     * @return 合伙人单量信息
     */
    @Override
	public MemberOrder selectMemberOrderById(Long orderId)
	{
	    return memberOrderMapper.selectMemberOrderById(orderId);
	}
	
	/**
     * 查询合伙人单量列表
     * 
     * @param memberOrder 合伙人单量信息
     * @return 合伙人单量集合
     */
	@Override
	public List<MemberOrder> selectMemberOrderList(MemberOrder memberOrder)
	{
	    return memberOrderMapper.selectMemberOrderList(memberOrder);
	}

	@Override
	public BigDecimal selectUserRewardTotal(Integer memberId) {
		return memberOrderMapper.selectUserRewardTotal(memberId);
	}

}
