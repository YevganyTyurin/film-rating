package com.epam.film.rating.filter;

import javax.servlet.*;
import java.io.IOException;

public class CharsetFilter implements Filter {

    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("requestEncoding");
        encoding = filterConfig.getInitParameter("requestEncoding");
        System.out.println("encoding = " +  encoding);
        if (encoding == null){
            encoding = "utf-8";
            System.out.println("utf-8");
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
//        servletResponse.setContentType("text/html;charset=UTF-8");
        servletRequest.setCharacterEncoding("utf-8");
        System.out.println("requestEncoding!!!!!!!!!!!!!!");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}