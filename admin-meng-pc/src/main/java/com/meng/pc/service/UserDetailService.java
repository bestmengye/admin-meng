package com.meng.pc.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author mengye
 * @Desc
 * @date 2020/5/21 16:11
 */
@Component
public class UserDetailService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("执行用户登录");
        return new User(s,"123456", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
