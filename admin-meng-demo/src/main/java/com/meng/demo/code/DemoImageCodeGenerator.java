package com.meng.demo.code;

import com.meng.core.validate.code.ImageCode;
import com.meng.core.validate.code.ValidateCodeGenerator;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author mengye
 * @desc
 * @date 2020/12/23 18:04
 */
@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {


    @Override
    public ImageCode generatorImageCode(ServletWebRequest request) {
        System.out.println("自定义的 图形验证码生成的代码");
        return null;
    }
}
