package com.meng.core.social.qq.connet;

import com.meng.core.social.qq.api.QQ;
import com.meng.core.social.qq.api.QQimpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * @author mengye
 * @desc QQ服务接口提供
 * @date 2021/1/28 16:45
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private String appId;

    /**
     * QQ获取授权的页面
     */
    public static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

    /**
     * 根据授权Authorization Code获取Access Token 地址
     */
    public static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    public QQServiceProvider(String appId, String appSecret) {
        super(new QQOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
        this.appId = appId;
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQimpl(accessToken, appId);
    }
}
