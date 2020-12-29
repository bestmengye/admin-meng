package com.meng.demo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author mengye
 * @Desc  计时过滤器
 * @date 2020/5/18 15:25
 */
@Component
public class TimeFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(TimeFilter.class);

    @Override
    public void init(FilterConfig filterConfig){
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
