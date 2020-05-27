package com.meng.core.pc.config;

import com.meng.core.pc.authentication.AdminAuthenticationFailureHandler;
import com.meng.core.pc.authentication.AdminAuthenticationSuccessHandler;
import com.meng.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author mengye
 * @Desc
 * @date 2020/5/21 15:09
 */
@Configuration
public class PcSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AdminAuthenticationSuccessHandler adminAuthenticationSuccessHandler;

    @Autowired
    private AdminAuthenticationFailureHandler adminAuthenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage("/authentication/require")
                //处理登录的请求
                .loginProcessingUrl("/admin-meng/login")
                .successHandler(adminAuthenticationSuccessHandler)
                .failureHandler(adminAuthenticationFailureHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require",
                        securityProperties.getPc().getLoginPage()) .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }
}
