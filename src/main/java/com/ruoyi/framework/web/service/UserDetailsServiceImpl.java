package com.ruoyi.framework.web.service;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.framework.security.PasswordService;
import com.ruoyi.framework.web.domain.JwtUser;
import com.ruoyi.project.wallet.member.domain.Member;
import com.ruoyi.project.wallet.member.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IMemberService memberService;
    @Autowired
    PasswordService passwordService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberService.selectMemberByLoginName(username);

        return new JwtUser(member);
    }

}
