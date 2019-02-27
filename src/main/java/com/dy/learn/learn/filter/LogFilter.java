package com.dy.learn.learn.filter;

import com.dy.learn.learn.controller.IndexController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
@Component
@WebFilter(urlPatterns={"/*"},filterName ="logFilter")
public class LogFilter implements Filter {
    Logger logger=LoggerFactory.getLogger(IndexController.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("logFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("logFilter init");
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        logger.info("IP:" + request.getRemoteHost());
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        logger.info("destroy");
    }
}
