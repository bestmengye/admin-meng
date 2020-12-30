package com.meng.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author mengye
 * @desc 验证码生成器
 * @date 2020/12/23 17:54
 */
public interface ValidateCodeGenerator {

    /**
     * 生成验证码
     *
     * @param request
     * @return
     */
    ValidateCode generator(ServletWebRequest request);
}
