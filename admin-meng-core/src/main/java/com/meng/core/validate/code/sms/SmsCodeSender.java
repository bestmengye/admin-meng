package com.meng.core.validate.code.sms;

/**
 * @author mengye
 * @desc
 * @date 2020/12/29 10:31
 */
public interface SmsCodeSender {

    /**
     * 发送验证码
     *
     * @param mobile 手机
     * @param code 验证码
     */
    void send(String mobile, String code);
}
