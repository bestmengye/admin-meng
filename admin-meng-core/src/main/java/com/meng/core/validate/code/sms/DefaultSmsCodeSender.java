package com.meng.core.validate.code.sms;

/**
 * @author mengye
 * @desc
 * @date 2020/12/29 13:50
 */
public class DefaultSmsCodeSender implements SmsCodeSender {

    @Override
    public void send(String mobile, String code) {
        System.out.println("像手机号" + mobile + "发送验证码:" + code);
    }
}
