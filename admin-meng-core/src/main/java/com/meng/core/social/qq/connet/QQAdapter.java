package com.meng.core.social.qq.connet;

import com.meng.core.social.qq.api.QQ;
import com.meng.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @author mengye
 * @desc
 * @date 2021/1/28 17:03
 */
public class QQAdapter implements ApiAdapter<QQ> {

    /**
     * 测试api是否可用
     *
     * @param qq
     * @return
     */
    @Override
    public boolean test(QQ qq) {
        return true;
    }

    /**
     * @param api
     * @param values
     */
    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
        QQUserInfo userInfo = api.getUserInfo();

        // 设置连接用户信息的值
        values.setDisplayName(userInfo.getNickname());
        values.setImageUrl(userInfo.getFigureurl_qq_1());
        // 主页. QQ没有
        values.setProfileUrl(null);
        values.setProviderUserId(userInfo.getOpenId());

    }

    /**
     * 拿到一个具体的用户信息
     *
     * @param api
     * @return
     */
    @Override
    public UserProfile fetchUserProfile(QQ api) {
        return null;
    }

    /**
     * 修改状态和消息 像微博有更新动态 时间线等..
     *
     * @param api
     * @param s
     */
    @Override
    public void updateStatus(QQ api, String s) {
        // do noting
    }
}
