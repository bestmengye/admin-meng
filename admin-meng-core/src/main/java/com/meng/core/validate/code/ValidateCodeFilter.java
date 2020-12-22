package com.meng.core.validate.code;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author mengye
 * @desc 图片验证码拦截器 oncePerRequestFilter: spring security保证他只会被调用一次
 * @date 2020/12/22 16:36
 */
public class ValidateCodeFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);
        if (StringUtils.equals("/admin-meng/login", request.getRequestURI())
                && StringUtils.equalsIgnoreCase(request.getMethod(), "post")) {
            try {
                // 验证当前请求中
                validate(new ServletWebRequest(request));
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * 校验request中验证码逻辑
     *
     * @param request
     */
    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        ImageCode imageCodeInSession = (ImageCode) sessionStrategy.getAttribute(request, ValidateCodeController.SESSION_KEY_IMAGE_CODE);

        // 获取在request中的 输入的验证码
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码不能为空");
        }

        if (codeInRequest == null) {
            throw new ValidateCodeException("验证码不不存在");
        }

        if (imageCodeInSession.isExpired()) {
            sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY_IMAGE_CODE);
            throw new ValidateCodeException("验证码已经过期");
        }
        if (!StringUtils.equals(imageCodeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }

        // 最后删除 session中的验证码
        sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY_IMAGE_CODE);
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }
}
