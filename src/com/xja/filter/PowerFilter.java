package com.xja.filter;

import com.mysql.cj.Session;
import com.xja.bean.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author GodLamp
 * @date 2022/8/17 15:01
 */
public class PowerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");

        String path = request.getContextPath() + request.getServletPath();

        if (user == null){
            filterChain.doFilter(request,response);
            return;
        }

        if ( user.getPower() == 1){
            //管理员
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

        }


    }

    @Override
    public void destroy() {

    }
}
