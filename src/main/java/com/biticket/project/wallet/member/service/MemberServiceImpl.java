package com.biticket.project.wallet.member.service;

import java.util.List;

import com.biticket.common.utils.security.EncryptUtils;
import com.biticket.framework.security.PasswordService;
import com.biticket.project.wallet.userTransSerial.mapper.UserTransSerialMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.biticket.project.wallet.member.mapper.MemberMapper;
import com.biticket.project.wallet.member.domain.Member;

/**
 * 会员 服务层实现
 *
 * @author admin
 * @date 2018-08-25
 */
@Service
public class MemberServiceImpl implements IMemberService {
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private UserTransSerialMapper transSerialMapper;

    @Autowired
    private PasswordService passwordService;

    /**
     * 查询会员信息
     *
     * @param memberId 会员ID
     * @return 会员信息
     */
    @Override
    public Member selectMemberById(Integer memberId) {
        return memberMapper.selectMemberById(memberId);
    }

    /**
     * 查询会员列表
     *
     * @param member 会员信息
     * @return 会员集合
     */
    @Override
    public List<Member> selectMemberList(Member member) {
        return memberMapper.selectMemberList(member);
    }

    /**
     * 新增会员
     *
     * @param member 会员信息
     * @return 结果
     */
    @Override
    public int insertMember(Member member) {
        member.randomSalt();

        if(null!=member.getParenter())
        {
            Member pMember = memberMapper.selectMemberByMobile(member.getParenter());
            if(null!=pMember)
            {
                member.setPId(pMember.getMemberId());
                member.setCommunityId(pMember.getCommunityId());

                if(null!=pMember.getRealIds() && !"".equals(pMember.getRealIds()))
                {
                    member.setRealIds(pMember.getRealIds() + ","+String.valueOf(pMember.getMemberId()));
                }else{
                    member.setRealIds(String.valueOf(pMember.getMemberId()));
                }
            }
        }

        member.setTradePassword(passwordService.encryptPassword(member.getLoginName(), member.getTradePassword(), member.getSalt()));
        member.setWalletAddress(EncryptUtils.createSecretKey(member.getLoginName(),member.getSalt()));
        return memberMapper.insertMember(member);
    }

    /**
     * 修改会员
     *
     * @param member 会员信息
     * @return 结果
     */
    @Override
    public int updateMember(Member member) {

        return memberMapper.updateMember(member);
    }

    @Override
    public Member selectMemberByName(String loginName) {

            return memberMapper.selectMemberByName(loginName);
    }

    /***
     * 会员通过登录名登录
     * @param memberName
     * @return
     */
    @Override
    public Member selectMemberByLoginName(String memberName) {
        return memberMapper.selectMemberByLoginName(memberName);
    }

    /***
     * 会员通过手机号码登录
     * @param memberName
     * @return
     */
    @Override
    public Member selectMemberByMobile(String memberName) {
        return memberMapper.selectMemberByMobile(memberName);
    }

    /**
     * 重置密码
     * @param member
     * @return
     */
    @Override
    public int resetMemberPwd(Member member) {
        //TODO 需要先验证输入的密码是否有效
        int i;
        i = memberMapper.updateMember(member);
        return i;
    }

    @Override
    public boolean isExitsLoginName(String loginName) {
        return  null==memberMapper.selectMemberByLoginName(loginName)?false:true;
    }

    @Override
    public int selectMemberCount(Member pMember) {
        return memberMapper.selectMemberCount(pMember);
    }

    @Override
    public List<Member> selectChilderMemberList(Member pMember) {
        return memberMapper.selectChilderMemberList( pMember) ;
    }

    @Override
    public int changeMemberAmount(List<Member> memberList) {
        return memberMapper.changeMemberAmount(memberList);
    }


}
