package com.meng.pc.controller;

import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author mengye
 * @Desc
 * @date 2020/5/22 14:17
 */
@Component
public class PcSecurityController {

    private RequestCache requestCache=new HttpSessionRequestCache();

    @RequestMapping("/auth/require")
    public void authRequire(HttpServletRequest request, HttpServletResponse response){

    }


}
