package com.meng.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author mengye
 * @desc 图片验证码
 * @date 2020/12/22 15:54
 */
@RestController
public class ValidateCodeController {

    /**
     * session图片验证码key
     */
    public static final String SESSION_KEY_IMAGE_CODE = "SESSION_KEY_IMAGE_CODE";

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private ValidateCodeGenerator imageCodeGenerator;

    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = imageCodeGenerator.generatorImageCode(new ServletWebRequest(request));
        sessionStrategy.setAttribute(new ServletRequestAttributes(request), SESSION_KEY_IMAGE_CODE, imageCode);
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }


}
