package com.xja.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author GodLamp
 * @date 2022/8/9 10:43
 */
public class EncodingFilter implements Filter {
    private String code;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        code = filterConfig.getInitParameter("coding");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(code);
        servletResponse.setCharacterEncoding(code);

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
