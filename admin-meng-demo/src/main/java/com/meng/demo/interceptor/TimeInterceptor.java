package com.meng.demo.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author mengye
 * @Desc time拦截器
 * @date 2020/5/18 16:58
 */
@Slf4j
@Component
public class TimeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        System.out.println("Interceptor pre Handle");
        request.setAttribute("startTime", LocalDateTime.now());
        HandlerMethod method= (HandlerMethod) o;
        System.out.println("Interceptor bean名称:"+method.getBean().getClass().getName());
        System.out.println("Interceptor beanType"+method.getBeanType());
        System.out.println("Interceptor"+method);
        System.out.println("Interceptor"+method.toString());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("Interceptor post Handle");
        LocalDateTime now = (LocalDateTime) request.getAttribute("startTime");
        Duration duration=Duration.between(now,LocalDateTime.now());
        System.out.println("Interceptor post Handle 耗时"+duration.toMillis());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
        System.out.println("Interceptor after Completion");
        LocalDateTime now = (LocalDateTime) request.getAttribute("startTime");
        Duration duration=Duration.between(now,LocalDateTime.now());
        System.out.println("Interceptor after Handle 耗时"+duration.toMillis());
    }
}
