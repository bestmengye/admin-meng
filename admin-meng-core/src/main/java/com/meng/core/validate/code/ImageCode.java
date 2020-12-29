package com.meng.core.validate.code;

import java.awt.image.BufferedImage;

/**
 * @author mengye
 * @desc 图片验证码
 * @date 2020/12/22 15:52
 */
public class ImageCode extends ValidateCode {

    /**
     * 图片
     */
    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, int expireSecond) {
        super(code, expireSecond);
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
