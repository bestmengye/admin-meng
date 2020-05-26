package com.meng.core.config;

import com.meng.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author mengye
 * @desc 配置文件 加载SecurityProperties属性配置
 * @date 2020/5/2621:46
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {

}
