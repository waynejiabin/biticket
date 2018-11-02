package com.ruoyi.project.wallet.memberOrder.mapper;

import com.ruoyi.project.wallet.memberOrder.domain.MemberOrder;

import java.math.BigDecimal;
import java.util.List;	

/**
 * 合伙人单量 数据层
 * 
 * @author ruoyi
 * @date 2018-09-08
 */
public interface MemberOrderMapper 
{
	/**
     * 查询合伙人单量信息
     * 
     * @param orderId 合伙人单量ID
     * @return 合伙人单量信息
     */
	MemberOrder selectMemberOrderById(Long orderId);
	
	/**
     * 查询合伙人单量列表
     * 
     * @param memberOrder 合伙人单量信息
     * @return 合伙人单量集合
     */
	 List<MemberOrder> selectMemberOrderList(MemberOrder memberOrder);


	BigDecimal selectUserRewardTotal(Integer memberId);
}