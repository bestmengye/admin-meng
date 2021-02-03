package com.meng.demo.security;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * @author mengye
 * @desc 将第三方社交信息 注册成自己系统中用户
 * @date 2021/2/3 13:53
 */
@Component
public class DemoConnectionSignUp implements ConnectionSignUp {

    @Override
    public String execute(Connection<?> connection) {
        // 根据 第三方社交信息 默认创建 系统用户的唯一标识
        return connection.getDisplayName();
    }
}
