package com.meng.demo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author mengye
 * @Desc
 * @date 2020/5/18 15:25
 */
//@Component
public class TimeFilter implements Filter {


    Logger logger=LoggerFactory.getLogger(TimeFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        logger.info("filter start");
        LocalDateTime start = LocalDateTime.now();
        chain.doFilter(servletRequest,servletResponse);
        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start,end);
        logger.info("timerFilter耗时:"+duration.toMillis());
        logger.info("filter finish");
    }

    @Override
    public void destroy() {
        logger.info("filter destroy");
    }
}
