package com.meng.core.validate.code;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author mengye
 * @desc
 * @date 2020/12/22 15:52
 */
@Data
public class ImageCode {

    /**
     * 图形验证码
     */
    private BufferedImage image;

    /**
     * 验证码
     */
    private String code;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    public ImageCode(BufferedImage bufferedImage, String code, int expireSecond) {
        this.image = bufferedImage;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireSecond);
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
