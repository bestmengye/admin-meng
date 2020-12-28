package com.meng.core.pc.config;

import com.meng.core.pc.authentication.AdminAuthenticationFailureHandler;
import com.meng.core.pc.authentication.AdminAuthenticationSuccessHandler;
import com.meng.core.properties.SecurityProperties;
import com.meng.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @author mengye
 * @Desc
 * @date 2020/5/21 15:09
 */
@Configuration
public class PcSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AdminAuthenticationSuccessHandler adminAuthenticationSuccessHandler;

    @Autowired
    private AdminAuthenticationFailureHandler adminAuthenticationFailureHandler;

    /**
     * 注入数据源
     */
    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 图片验证码
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(adminAuthenticationFailureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();

        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                    .loginPage("/authentication/require")
                    //处理登录的请求
                    .loginProcessingUrl("/admin-meng/login")
                    //登录成功的处理
                    .successHandler(adminAuthenticationSuccessHandler)
                    //登录失败的处理
                    .failureHandler(adminAuthenticationFailureHandler)
                    .and()
                .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(securityProperties.getPc().getRememberMeSeconds())
                    .userDetailsService(userDetailsService)
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require",
                        securityProperties.getPc().getLoginPage(), "/code/image").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }
}
