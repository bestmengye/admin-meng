package com.meng.core.authentication;

import com.meng.core.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

/**
 * @author mengye
 * @desc 密码抽象安全配置
 * @date 2021/1/27 11:07
 */
public class AbstractPasswordChannelSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AdminAuthenticationSuccessHandler adminAuthenticationSuccessHandler;

    @Autowired
    private AdminAuthenticationFailureHandler adminAuthenticationFailureHandler;

    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
        http.formLogin()
                // 设置登陆页面
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                // 处理登录的请求
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL)
                .successHandler(adminAuthenticationSuccessHandler)
                .failureHandler(adminAuthenticationFailureHandler);
    }
}
