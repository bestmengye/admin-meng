package com.meng.core.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author mengye
 * @desc 安全属性的配置
 * @date 2020/5/2621:46
 */
@Data
@ConfigurationProperties(prefix = "admin.meng")
public class SecurityProperties {

    /**
     * 浏览器属性配置
     */
    private PcProperties pc = new PcProperties();


    /**
     * 验证码验证的属性配置
     */
    private ValidateCodeProperties code = new ValidateCodeProperties();

    /**
     * 第三方属性配置
     */
    private SocialProperties social = new SocialProperties();



    public PcProperties getPc() {
        return pc;
    }

    public void setPc(PcProperties pc) {
        this.pc = pc;
    }

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }

    public SocialProperties getSocial() {
        return social;
    }

    public void setSocial(SocialProperties social) {
        this.social = social;
    }
}
