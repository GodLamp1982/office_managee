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
        System.out.println(path);

        if (user == null){
            filterChain.doFilter(request,response);
            return;
        }

        if ( user.getPower() == 0){
            System.out.println("1");//1
            //普通账号
            if(path.contains("/start") ||
                    "/orderdish/order".equals(path) ||
                    "/orderdish/dish".equals(path) ||
                    "/orderdish/user".equals(path) ||
                    path.contains("/order?") ||
                    path.contains("/dish?") ||
                    path.contains("/user?") ||
                    path.contains("/login") ||
                    path.contains("/index") ||
                    path.contains("/forgotpwd") ||
                    path.contains("/register") ||
                    path.contains("/images") ||
                    path.contains("/error") ||
                    path.contains("/css") ||
                    path.contains("/js") ||
                    path.contains("/ordercar.jsp") ||
                    path.contains("/orderdish.jsp") ||
                    path.contains("/persondata.jsp") ||
                    path.contains("/showsingledetail.jsp")
            ){
                System.out.println("2");//2
                filterChain.doFilter(servletRequest,servletResponse);
                return;
            } else {
                System.out.println("3");//3
                response.sendRedirect(request.getContextPath() + "/start.jsp");
                return;
            }
        } else {
            System.out.println("4");//4
            //管理员
            if(path.contains("/start") ||
                    "/orderdish/order".equals(path) ||
                    "/orderdish/dish".equals(path) ||
                    "/orderdish/user".equals(path) ||
                    path.contains("/order?") ||
                    path.contains("/dish?") ||
                    path.contains("/user?") ||
                    path.contains("/login") ||
                    path.contains("/index") ||
                    path.contains("/forgotpwd") ||
                    path.contains("/register") ||
                    path.contains("/images") ||
                    path.contains("/error") ||
                    path.contains("/css") ||
                    path.contains("/js") ||
                    path.contains("/adddishtype.jsp") ||
                    path.contains("/adddish.jsp") ||
                    path.contains("/dishmanage.jsp") ||
                    path.contains("/showdishtype.jsp") ||
                    path.contains("/updatedishinfo.jsp") ||
                    path.contains("/userorderingdetail.jsp") ||
                    path.contains("/usershow.jsp")
            ){
                System.out.println("5");//5
                filterChain.doFilter(servletRequest,servletResponse);
                return;
            } else {
                System.out.println("6");//6
                response.sendRedirect(request.getContextPath() + "/start.jsp");
                return;
            }
        }


    }

    @Override
    public void destroy() {

    }
}
