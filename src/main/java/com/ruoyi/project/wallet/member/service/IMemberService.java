package com.ruoyi.project.wallet.member.service;

import com.ruoyi.project.wallet.member.domain.Member;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * 会员 服务层
 * 
 * @author ruoyi
 * @date 2018-08-25
 */
public interface IMemberService 
{
	/**
     * 查询会员信息
     * 
     * @param memberId 会员ID
     * @return 会员信息
     */
	 Member selectMemberById(Integer memberId);
	
	/**
     * 查询会员列表
     * 
     * @param member 会员信息
     * @return 会员集合
     */
	 List<Member> selectMemberList(Member member);
	
	/**
     * 新增会员
     * 
     * @param member 会员信息
     * @return 结果
     */
	 int insertMember(Member member);
	
	/**
     * 修改会员
     * 
     * @param member 会员信息
     * @return 结果
     */
	 int updateMember(Member member);

	/**
	 * 根据用户名获取用户
	 * @param loginName
	 * @return
	 */
    Member selectMemberByName(String loginName);

	/***
	 * 会员通过登录名登录
	 * @param memberName
	 * @return
	 */
	Member selectMemberByLoginName(String memberName);

	/***
	 * 会员通过手机号码登录
	 * @param memberName
	 * @return
	 */

	Member selectMemberByMobile(String memberName);

	/**
	 * 重置密码
	 * @param member
	 * @return
	 */
	int resetMemberPwd(Member member);


	/***
	 * 账号是否存在
	 * @param loginName
	 * @return
	 */
	boolean isExitsLoginName(String loginName);


    int selectMemberCount(Member pMember);

    List<Member> selectChilderMemberList(Member pMember);

	/**
	 * 批量更新账户
	 * @param memberList
	 * @return
	 */
	int changeMemberAmount(List<Member> memberList);
}
