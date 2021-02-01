package com.meng.core.validate.code;

import com.meng.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author mengye
 * @desc 图片验证码拦截器 oncePerRequestFilter: spring security保证他只会被调用一次
 * @date 2020/12/22 16:36
 */
@Slf4j
public class SmsCodeFilter extends OncePerRequestFilter implements InitializingBean {

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    private Set<String> urls = new HashSet<>();

    private SecurityProperties securityProperties;

    /**
     * 路径匹配
     */
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        logger.info(requestURI);

        boolean action = false;
        // 匹配设置的路径
        for (String url : urls) {
            if (antPathMatcher.match(url, request.getRequestURI())) {
                action = true;
            }
        }

        if (action) {
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
        ValidateCode validateCode = (ValidateCode) sessionStrategy.getAttribute(request,
                ValidateCodeController.SESSION_KEY_CODE + "SMS");

        // 获取在request中的 输入的验证码
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "smsCode");

        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码不能为空");
        }

        if (codeInRequest == null || validateCode == null) {
            throw new ValidateCodeException("验证码不存在");
        }

        if (!StringUtils.equals(validateCode.getCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }

        if (validateCode.isExpired()) {
            sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY_CODE + "SMS");
            throw new ValidateCodeException("验证码已经过期");
        }

        // 最后删除 session中的验证码
        sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY_CODE + "SMS");
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String[] configUrl = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getSmsCode().getUrl(), ",");
        if (configUrl != null && configUrl.length > 0) {
            for (String s : configUrl) {
                urls.add(s);
            }
        }
        urls.add("/admin-meng/mobile");
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
