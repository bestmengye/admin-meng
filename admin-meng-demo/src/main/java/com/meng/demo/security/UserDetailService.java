package com.meng.demo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;


/**
 * @author mengye
 * @Desc 自定义实现的userDetails
 * @date 2020/5/21 16:11
 */
@Component
public class UserDetailService implements UserDetailsService, SocialUserDetailsService {

    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private PasswordEncoder passwordEncoder;


    /**
     * 使用用户名密码登录的默认走userNameAndPassword拦截器 然后调用自身的 访问数据库以及代码处理验证
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("表单登录的用户名:{}", username);
        return builderUser(username);
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        logger.info("社交登录用户id:{}", userId);
        return builderUser(userId);
    }

    private SocialUserDetails builderUser(String userId) {

        String password = passwordEncoder.encode("123456");
        logger.info("密码:{}", password);
        return new SocialUser(userId, password,
                true,
                true,
                true,
                true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
