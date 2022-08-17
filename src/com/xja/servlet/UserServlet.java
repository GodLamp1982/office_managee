package com.xja.servlet;

import com.xja.bean.User;
import com.xja.common.Page;
import com.xja.common.ReturnValue;
import com.xja.common.StateCode;
import com.xja.service.impl.UserServiceImpl;
import com.xja.util.MD5Util;
import com.xja.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author GodLamp
 * @date 2022/8/13 14:32
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {
    UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action){
            case "login":
                loginVali(request,response);
                break;
            case "code":
                getValiCode(request,response);
                break;
            case "register":
                registerAccount(request,response);
                //看judge是否为空，为空则代表当前正在进行用户注册
                //不为空代表当前是管理员正在进行用户添加
                Integer judge = (Integer) request.getSession().getAttribute("judge");
                if (judge == null){
                    request.getRequestDispatcher("login.jsp").forward(request,response);
                } else {
                    request.getSession().removeAttribute("judge");
                    request.getRequestDispatcher("user?action=allUser").forward(request,response);
                }
                break;
            case "updateUserData":
                updateUserData(request,response);
                break;
            case "allUser":
                findAllUser(request,response);
                break;
            case "quitSystem":
                quitSystem(request,response);
                break;
            case "oneUserFind":
                findOneUserById(request,response);
                break;
            case "managerDelUser":
                managerDelUser(request,response);
                break;
            case "valiAccountHasExist":
                valiAccountHasExist(request,response);
                break;
            case "forgetPassword":
                forgetPasswordSetNewPwd(request,response);
                break;
            default:
                break;
        }
    }

    /**
     * 忘记密码
     * @param request
     * @param response
     */
    private void forgetPasswordSetNewPwd(HttpServletRequest request, HttpServletResponse response) {
        int judge = userService.setNewPassword(request.getParameter("account"),request.getParameter("pwd"),request.getParameter("userName"),request.getParameter("tel"));

        try {
            if (judge == 1){
                request.getRequestDispatcher("login.jsp").forward(request,response);
            } else {
                request.getRequestDispatcher("error/updatepwderror.jsp").forward(request,response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * 忘记密码时验证其账号是否存在
     * @param request
     * @param response
     */
    private void valiAccountHasExist(HttpServletRequest request, HttpServletResponse response) {
        String account = request.getParameter("account");

        try {
            response.getWriter().write(userService.findByAccount(account));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 删除账号
     * @param request
     * @param response
     */
    private void managerDelUser(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userId");
        if (userId == null){
            return;
        }
        userService.delByUserId(Integer.parseInt(userId));
        try {
            request.getRequestDispatcher("user?action=allUser").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查找用户
     * @param request
     * @param response
     */
    private void findOneUserById(HttpServletRequest request, HttpServletResponse response) {
        String account = request.getParameter("account");
        if (account == null){
            return;
        }

        request.setAttribute(
                "managerUpdatePersonData",
                userService.findUserByAccount(account)
        );

        try {
            request.getRequestDispatcher("view/persondata.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询所有用户
     * @param request
     * @param response
     */
    private void findAllUser(HttpServletRequest request, HttpServletResponse response) {
        String currentPage = request.getParameter("currentPage");

        int pageIndex = (currentPage == null ? 1 : Integer.parseInt(currentPage));

        ReturnValue allUserByPaging = userService.findAllUserByPaging(pageIndex);

        Page page = allUserByPaging.getPage();
        request.setAttribute("allUser",allUserByPaging.getUserList());

        //分页
        //总页数
        request.setAttribute("allCount",page.getPageCount());
        //上一页
        request.setAttribute("preIndex",page.getPreIndex());
        //下一页
        request.setAttribute("nextIndex",page.getNextIndex());

        try {
            request.getRequestDispatcher("view/usershow.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 退出系统
     * @param request
     * @param response
     */
    private void quitSystem(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();

        try {
            response.sendRedirect("login.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改个人用户资料
     * @param request
     * @param response
     */
    private void updateUserData(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("currentUser");

        Map<String,String> map = new HashMap<>();
        map.put("power",request.getParameter("power"));
        map.put("pwd",request.getParameter("pwd"));
        map.put("userId",request.getParameter("userId"));
        map.put("userName",request.getParameter("userName"));
        map.put("account",request.getParameter("account"));
        map.put("tel",request.getParameter("tel"));
        map.put("address",request.getParameter("address"));

        userService.update(map,user);

        try {
            if ( request.getSession().getAttribute("managerUpdateJudge") == null ){
                //当前为普通用户，退回到首页
                request.getSession().invalidate();
                response.sendRedirect("login.jsp");
            } else {
                request.getSession().removeAttribute("judge");
                request.getRequestDispatcher("user?action=allUser").forward(request,response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }

    }

    /**
     * 注册账号
     * @param request
     * @param response
     */
    private void registerAccount(HttpServletRequest request, HttpServletResponse response) {
        String power = request.getParameter("pow");
        String account = request.getParameter("account");
        if (account == null){
            return;
        }

        int pow = (power == null ? 0 : Integer.parseInt(power));

        String result = userService.findByAccount(account);
        if ("true".equals(result)){
            //注册账号已存在，不能重复
            request.setAttribute("accountHasExist","账号已存在！");
            return;
        }
        int re = userService.addAccount(
                new User(
                        0,
                        request.getParameter("userName"),
                        request.getParameter("account"),
                        request.getParameter("pwd"),
                        request.getParameter("tel"),
                        request.getParameter("address"),
                        pow
                )
        );
        if (re < 1){
            try {
                request.getRequestDispatcher("error/registererror.jsp").forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 生成验证码
     * @param request
     * @param response
     */
    private void getValiCode(HttpServletRequest request, HttpServletResponse response) {
        String code = Utils.randomNums(4);
        request.getSession().setAttribute("correctCode",code);
        try {
            response.getWriter().write(
                    code
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证登录
     * @param request
     * @param response
     */
    private void loginVali(HttpServletRequest request, HttpServletResponse response) {
        String account = request.getParameter("account");
        String pwd = request.getParameter("pwd");
        String code = request.getParameter("code");
        String correctCode = (String) request.getSession().getAttribute("correctCode");

        if (account == null || correctCode == null || pwd == null || code == null){
            try {
                request.getRequestDispatcher(request.getContextPath() + "/login.jsp").forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        User user = userService.findUserByAccount(account);

        //清除警告信息
        request.getSession().removeAttribute("codeError");
        request.getSession().removeAttribute("userError");
        request.getSession().removeAttribute("pwdError");

        try{
            //验证验证码
            if( !correctCode.equals(code) ){
                request.getSession().setAttribute("codeError", StateCode.LOGIN_VERIFYCODE_ERROE);
                request.getRequestDispatcher("error/loginerror.jsp").forward(request,response);
            } else {
                //判断是否存在该用户
                if(user == null){
                    request.getSession().setAttribute("userError", StateCode.LOGIN_NO_ACCOUNT);
                    request.getRequestDispatcher("error/loginerror.jsp").forward(request,response);
                } else {
                    //验证密码
                    if ( !(user.getPassword()).equals(MD5Util.getMd5Str(pwd)) ){
                        request.getSession().setAttribute("pwdError",StateCode.LOGIN_PWD_ERROR);
                        request.getRequestDispatcher("error/loginerror.jsp").forward(request,response);
                    } else {
                        //登录成功
                        request.getSession().setAttribute("currentUser",user);
                        //普通账号与管理员账号显示不同的界面
                        if ( user.getPower() == 0){
                            //普通账号
                            //不是管理员则删除此标志
                            request.getSession().removeAttribute("managerUpdateJudge");
                            request.getRequestDispatcher("dish?action=generalUserIndex").forward(request,response);
                        } else {
                            //管理员账号
                            //代表当前为管理员登录
                            request.getSession().setAttribute("managerUpdateJudge",1);
                            request.getRequestDispatcher("view/admin.jsp").forward(request,response);
                        }

                    }
                }
            }

        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
