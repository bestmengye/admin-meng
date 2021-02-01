package com.meng.core.social;

import com.meng.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @author mengye
 * @desc social相关配置
 * @date 2021/1/28 17:17
 */
@Configuration
@EnableSocial
@Slf4j
public class SocialConfig extends SocialConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        // 表名称不可变 可以加前缀
//        repository.setTablePrefix("imooc_");
        return repository;
    }

    @Bean
    public SpringSocialConfigurer adminMengSocialSecurityConfig() {
        String filterProcessUrl = securityProperties.getSocial().getFilterProcessUrl();
        log.info("------------处理的url" + filterProcessUrl);
        AdminMengSpringSocialConfigurer configurer = new AdminMengSpringSocialConfigurer(filterProcessUrl);
        return configurer;
    }
}
