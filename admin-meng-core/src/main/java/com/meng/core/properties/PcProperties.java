package com.meng.core.properties;

/**
 * @author mengye
 * @desc pc的属性配置文件
 * @date 2020/5/26 21:00
 */
public class PcProperties {

    private String loginPage="/login.html";

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }
}
