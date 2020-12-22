package com.meng.core.pc.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meng.core.pc.support.SimpleResponse;
import com.meng.core.properties.LoginType;
import com.meng.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
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
public class AdminAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        log.info("登陆失败"+objectMapper.writeValueAsString(exception));

        if(LoginType.JSON.equals(securityProperties.getPc().getLoginType())){
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(exception.getMessage())));
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }else {
            super.onAuthenticationFailure(request,response,exception);
        }

    }
}
