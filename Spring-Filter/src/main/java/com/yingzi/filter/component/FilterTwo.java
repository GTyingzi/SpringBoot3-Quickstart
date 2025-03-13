package com.yingzi.filter.component;

import jakarta.servlet.*;

import java.io.IOException;

/**
 * @author yingzi
 * @date 2025/3/10:20:31
 */
public class FilterTwo implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("FilterTwo：in");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("FilterTwo：out");
    }
}