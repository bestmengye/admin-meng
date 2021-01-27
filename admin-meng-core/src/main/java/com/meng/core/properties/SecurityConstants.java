package com.meng.core.properties;

/**
 * @author mengye
 * @desc 常用的默认字符串
 * @date 2021/1/27 11:15
 */
public interface SecurityConstants {

    /**
     * 默认的处理图形验证码的url前缀  /sms/code  /validate/code ...
     */
    String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/code";

    /**
     * 当需要身份请求身份认证的时候  默认跳转的url
     */
    String DEFAULT_UNAUTHENTICATION_URL = "/authentication/require";

    /**
     * 默认用户名密码处理登录的url
     */
    String DEFAULT_LOGIN_PROCESSING_URL = "/admin-meng/login";

    /**
     * 默认手机短信验证码处理登录的url
     */
    String DEFAULT_LOGIN_PROCESSING_URL_MOBILE = "/admin-meng/mobile";

    /**
     * 默认登录页面
     */
    String DEFAULT_LOGIN_URL = "/login.html";



}
