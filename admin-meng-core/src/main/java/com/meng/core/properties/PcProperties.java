package com.meng.core.properties;

/**
 * @author mengye
 * @desc pc的属性配置文件
 * @date 2020/5/26 21:00
 */
public class PcProperties {

    private String loginPage = "/login.html";

    /**
     * 自定义登录返回的 是json or 页面
     */
    private LoginType loginType = LoginType.JSON;

    /**
     * 记住我的过期时间
     */
    private int rememberMeSeconds = 3600;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }
}
