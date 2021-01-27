package com.meng.core.pc.controller;

import com.meng.core.properties.SecurityConstants;
import com.meng.core.properties.SecurityProperties;
import com.meng.core.pc.support.SimpleResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author mengye
 * @Desc 需要身份认证默认跳转的工具
 * @date 2020/5/22 14:17
 */
@Slf4j
@RestController
public class PcSecurityController {

    private RequestCache requestCache = new HttpSessionRequestCache();

    //spring 提供跳转的工具类
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 当需要身份认证跳转到这里
     *
     * @param request
     * @param response
     */
    @RequestMapping(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            if(StringUtils.endsWithIgnoreCase(targetUrl,".html")){
                log.info("需要匹配的地址{}",targetUrl);
                redirectStrategy.sendRedirect(request,response,securityProperties.getPc().getLoginPage());
            }
        }
        return new SimpleResponse("访问的地址需要身份认证,请引导到登陆页面");
    }


}
