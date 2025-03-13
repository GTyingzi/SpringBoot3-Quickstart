package com.yingzi.filter.component;

import jakarta.servlet.*;

import java.io.IOException;

/**
 * @author yingzi
 * @date 2025/3/10:20:29
 */
public class FilterOne implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("FilterOne：in");
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("FilterOne：out");
    }
}
