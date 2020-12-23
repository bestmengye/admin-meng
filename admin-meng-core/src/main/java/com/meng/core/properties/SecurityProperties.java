package com.meng.core.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author mengye
 * @desc 安全属性的配置
 * @date 2020/5/2621:46
 */
@Data
@ConfigurationProperties(prefix = "admin.meng")
public class SecurityProperties {

    private PcProperties pc = new PcProperties();

    private ValidateCodeProperties code = new ValidateCodeProperties();

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
}
