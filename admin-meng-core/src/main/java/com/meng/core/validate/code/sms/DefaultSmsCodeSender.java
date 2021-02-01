package com.meng.core.validate.code.sms;

import lombok.extern.slf4j.Slf4j;

/**
 * @author mengye
 * @desc
 * @date 2020/12/29 13:50
 */
@Slf4j
public class DefaultSmsCodeSender implements SmsCodeSender {

    @Override
    public void send(String mobile, String code) {
        log.info("像手机号" + mobile + "发送验证码:" + code);
    }
}
