package com.meng.core.properties;

/**
 * @author mengye
 * @desc 图形验证码配置
 * @date 2020/12/23 11:37
 */
public class ImageCodeProperties extends SmsCodeProperties {
    /**
     * 宽度
     */
    private int width = 67;

    /**
     * 高度
     */
    private int height = 23;

    public ImageCodeProperties() {
        setLength(4);
    }

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
}
