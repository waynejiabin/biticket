package com.ruoyi.project.wallet.member.mapper;

import com.ruoyi.project.wallet.member.domain.Member;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * 会员 数据层
 * 
 * @author ruoyi
 * @date 2018-08-25
 */
public interface MemberMapper 
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
	 *
	 * @param loginName
	 * @return
	 */
    Member selectMemberByName(String loginName);

	/**
	 *
	 * @param loginName
	 * @return
	 */
	Member selectMemberByLoginName(String loginName);
	/**
	 *
	 * @param memberMobile
	 */
	Member selectMemberByMobile(String memberMobile);

	/**
	 *
	 * @param memberId
	 * @return
	 */
    BigDecimal selectMemberPreDayOutById(Integer memberId);

    int selectMemberCount(Member member);

    List<Member> selectChilderMemberList(Member pMember);

    int changeMemberAmount(@Param("memberList") List<Member> memberList);
}