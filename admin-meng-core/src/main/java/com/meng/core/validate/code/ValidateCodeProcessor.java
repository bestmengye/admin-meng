package com.meng.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author mengye
 * @desc 验证码处理接口
 * @date 2020/12/30 14:51
 */
public interface ValidateCodeProcessor {

    /**
     * session图片验证码key
     */
    String SESSION_KEY_PREFIX = "SESSION_KEY_CODE_";

    void create(ServletWebRequest request) throws Exception;
}
