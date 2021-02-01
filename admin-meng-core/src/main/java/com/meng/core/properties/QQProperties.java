package com.meng.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @author mengye
 * @desc QQ属性配置
 * @date 2021/1/28 17:32
 */
public class QQProperties extends SocialProperties {



    private String providerId = "qq";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

}
