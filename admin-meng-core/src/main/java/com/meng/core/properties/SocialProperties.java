package com.meng.core.properties;

/**
 * @author mengye
 * @desc
 * @date 2021/1/28 17:34
 */
public class SocialProperties {
    /**
     * 跳转QQ登录拦击的url路径
     */
    private String filterProcessUrl = "/auth";

    private QQProperties qq = new QQProperties();

    public QQProperties getQq() {
        return qq;
    }

    public void setQq(QQProperties qq) {
        this.qq = qq;
    }

    public String getFilterProcessUrl() {
        return filterProcessUrl;
    }

    public void setFilterProcessUrl(String filterProcessUrl) {
        this.filterProcessUrl = filterProcessUrl;
    }
}
