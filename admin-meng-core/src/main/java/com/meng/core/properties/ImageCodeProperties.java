package com.meng.core.properties;

/**
 * @author mengye
 * @desc 图形验证码配置
 * @date 2020/12/23 11:37
 */
public class ImageCodeProperties {

    /**
     * 宽度
     */
    private int width = 67;

    /**
     * 高度
     */
    private int height = 23;

    /**
     * 验证码位数长度
     */
    private int length = 4;

    /**
     * 验证码过期时间
     */
    private int expireSecond = 60;

    /**
     * 需要验证码的url
     */
    private String url;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getExpireSecond() {
        return expireSecond;
    }

    public void setExpireSecond(int expireSecond) {
        this.expireSecond = expireSecond;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
