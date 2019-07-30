package com.company.project.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(1)
@WebFilter(filterName = "crossfilter", urlPatterns = "/*")
public class CrossFilter  implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * 解决跨域问题
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");
//      System.out.println("TestFilter1");
        filterChain.doFilter(servletRequest,servletResponse);
        response.setHeader("Access-Control-Allow-Headers","x-requested-with,Content-Type,Access-Token");
    }

    @Override
    public void destroy() {

    }
}
