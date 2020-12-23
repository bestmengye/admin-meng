package com.meng.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author mengye
 * @desc 验证码生成起
 * @date 2020/12/23 17:54
 */
public interface ValidateCodeGenerator {

    ImageCode generatorImageCode(ServletWebRequest request);
}
