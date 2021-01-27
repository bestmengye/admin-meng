package com.meng.core.config;

import com.meng.core.authentication.mobile.SmsCodeAuthenticationFilter;
import com.meng.core.authentication.mobile.SmsCodeAuthenticationProvider;
import com.meng.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author mengye
 * @desc  验证码安全配置 (短信验证码、登录 图形验证码)
 * @date 2021/1/27 15:54
 */
@Component
public class ValidateCodeSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    /**
     * 注入失败和成功的处理器 以及userDetails 信息
     */
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 对短信过滤器 设置 失败成功处理
        ValidateCodeFilter  validateCodeFilter = new ValidateCodeFilter();
//        validateCodeFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        validateCodeFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
//        validateCodeFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        // 加入短信验证的provider 把短信filter 处理放在用户名密码之后 拦截 查看是否支持
        http.addFilterAfter(validateCodeFilter, UsernamePasswordAuthenticationFilter.class);
    }
}