package com.meng.demo.code;

import com.meng.core.validate.code.ImageCode;
import com.meng.core.validate.code.ValidateCodeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author mengye
 * @desc 实现自己自定义的图形验证码
 * @date 2020/12/23 18:04
 */
//@Component("imageCodeGenerator")
@Slf4j
public class DemoImageCodeGenerator implements ValidateCodeGenerator {


    @Override
    public ImageCode generator(ServletWebRequest request) {
        log.info("自定义的 图形验证码生成的代码");
        return null;
    }
}
