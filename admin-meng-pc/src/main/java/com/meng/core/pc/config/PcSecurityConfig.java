package com.meng.core.pc.config;

import com.meng.core.authentication.AbstractPasswordChannelSecurityConfig;
import com.meng.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.meng.core.config.ValidateCodeSecurityConfig;
import com.meng.core.properties.SecurityConstants;
import com.meng.core.properties.SecurityProperties;
import com.meng.core.validate.code.SmsCodeFilter;
import com.meng.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @author mengye
 * @Desc 浏览器安全配置
 * @date 2020/5/21 15:09
 */
@Configuration
public class PcSecurityConfig extends AbstractPasswordChannelSecurityConfig {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 注入数据源
     */
    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    /**
     * 添加social配置
     */
    @Autowired
    private SpringSocialConfigurer springSocialConfigurer;

    @Bean
    public PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 图片验证码
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();

        // 短信验证码
        SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
        smsCodeFilter.setSecurityProperties(securityProperties);
        smsCodeFilter.afterPropertiesSet();

        // 添加密码登录的 成功 失败处理配置
        applyPasswordAuthenticationConfig(http);

        //短信登录的配置
        http.apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .apply(validateCodeSecurityConfig)
                .and()
                .apply(springSocialConfigurer)
                .and()
                .addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getPc().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
                .and()
                // 添加不用权限验证的url
                .authorizeRequests()
                .antMatchers(
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        securityProperties.getPc().getLoginPage(),
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
                        "/bookshop/*")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();
    }
}
