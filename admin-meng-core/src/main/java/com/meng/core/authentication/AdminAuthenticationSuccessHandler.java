package com.meng.core.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meng.core.properties.LoginType;
import com.meng.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author mengye
 * @desc 登陆成功的处理的handler
 * @date 2020/5/2719:58
 */
@Component
@Slf4j
public class AdminAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    /**
     * AuthenticationSuccessHandler接口: 当登录成功之后会调用的方法
     * <p>
     * SavedRequestAwareAuthenticationSuccessHandler类: 继承默认登录成功处理的类
     */

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        log.info("登陆成功" + authentication.getDetails());

        /*
         * 通过core包自定义 loginType
         * 来实现成功是否通过跳转还是返回json对象 以及跳转的网页
         */
        if (LoginType.JSON.equals(securityProperties.getPc().getLoginType())) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        } else {
            super.onAuthenticationSuccess(request,response,authentication);

        }
    }
}
