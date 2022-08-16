package com.xja.filter;

import com.xja.bean.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author GodLamp
 * @date 2022/8/9 17:12
 */
public class IndexFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String path = request.getContextPath() + request.getServletPath();

        if(path.contains("/start") ||
                path.contains("/user") ||
                path.contains("/dish") ||
                path.contains("/login") ||
                path.contains("/index") ||
                path.contains("/forgotpwd") ||
                path.contains("/register") ||
                path.contains("/images") ||
                path.contains("/error") ||
                path.contains(".css") ||
                path.contains(".js") ||
                path.contains("/font") ||
                path.contains("/order")
        ){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        if(currentUser == null){
            response.sendRedirect(request.getContextPath() + "/start.jsp");
            return;
        }

        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }

}
