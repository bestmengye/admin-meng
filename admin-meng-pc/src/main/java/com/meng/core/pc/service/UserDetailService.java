package com.meng.core.pc.service;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import sun.security.provider.MD5;


/**
 * @author mengye
 * @Desc
 * @date 2020/5/21 16:11
 */
@Component
public class UserDetailService implements UserDetailsService {

    Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("执行用户登录 用户名:{}",username);

        String password =passwordEncoder.encode("123456");
        logger.info("密码:{}",password);
        return new User(username, password,
                true,
                true,
                true,
                true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
//        return new User(username, "123456", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
