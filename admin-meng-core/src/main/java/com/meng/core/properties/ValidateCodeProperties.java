package com.meng.core.properties;

/**
 * @author mengye
 * @desc 验证码属性
 * @date 2020/12/23 11:47
 */

public class ValidateCodeProperties {

    /**
     * 图片验证码 properties
     */
    private ImageCodeProperties imageCode = new ImageCodeProperties();

    /**
     * 短信验证码 properties
     */
    private SmsCodeProperties smsCode = new SmsCodeProperties();

    public ImageCodeProperties getImageCode() {
        return imageCode;
    }

    public void setImageCode(ImageCodeProperties imageCode) {
        this.imageCode = imageCode;
    }

    public SmsCodeProperties getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(SmsCodeProperties smsCode) {
        this.smsCode = smsCode;
    }
}
