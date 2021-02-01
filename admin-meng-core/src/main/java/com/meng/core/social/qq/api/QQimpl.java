package com.meng.core.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

/**
 * @author mengye
 * @desc
 * @date 2021/1/28 16:21
 */
@Slf4j
public class QQimpl extends AbstractOAuth2ApiBinding implements QQ {

    /**
     * 获取OPENID的url
     */
    public static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    /**
     * 获取用户信息url
     */
    public static final String URL_GET_USER_INFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private String appId;

    private String openId;

    private ObjectMapper objectMapper = new ObjectMapper();

    public QQimpl(String accessToken, String appId) {
        // 调用父类的 构造函数  调用的时候 默认会带accessToken参数
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;

        // 格式化openId 的url
        String openIdUrl = String.format(URL_GET_OPENID, accessToken);

        String result = getRestTemplate().getForObject(openIdUrl, String.class);
        log.info("获取QQ返回结果:{}", result);

        this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");

    }

    /**
     * 获取qq的用户信息
     *
     * @return
     */
    @Override
    public QQUserInfo getUserInfo() {

        // 执行获取 QQ用户信息的url
        String openIdUrl = String.format(URL_GET_USER_INFO, appId, openId);
        String result = getRestTemplate().getForObject(openIdUrl, String.class);

        log.info("获取QQ用户信息：{}", result);
        QQUserInfo qqUserInfo = null;
        try {
            qqUserInfo = objectMapper.readValue(result, QQUserInfo.class);
            // 获取到qq 用户信息 设置openId
            qqUserInfo.setOpenId(this.openId);
            return qqUserInfo;
        } catch (IOException e) {
            throw new RuntimeException("获取用户信息失败!");
        }
    }
}
