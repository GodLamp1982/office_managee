package com.xja.servlet;

import com.xja.bean.DishExt;
import com.xja.bean.User;
import com.xja.common.BackValue;
import com.xja.common.Page;
import com.xja.service.impl.OrderInfoServiceImpl;
import com.xja.service.impl.UserServiceImpl;
import com.xja.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @author GodLamp
 * @date 2022/8/13 16:48
 */
@WebServlet("/order")
public class OrderInfoServlet extends HttpServlet {
    OrderInfoServiceImpl orderInfoService = new OrderInfoServiceImpl();
    UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action){
            case "addBatchOrder":
                addBatchOrderInfo(request,response);
                break;
            case "orderCar":
                findOrderCar(request,response);
                break;
            case "orderCarCommit":
                batchOrderCarCommit(request,response);
                break;
            case "deleteSelectedOrderCar":
                deleteSelectedOrderInCar(request,response);
                break;
            case "findAllOrder":
                findAllOrder(request,response);
                break;
            default:
                break;
        }
    }

    /**
     * 查询所有用户的订单号
     * @param request
     * @param response
     */
    private void findAllOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPage = request.getParameter("currentPage");

        int pageIndex = 0;
        if (currentPage == null || currentPage == ""){
            pageIndex = 1;
        } else {
            pageIndex = Integer.parseInt(currentPage);
        }

        //全部用户
        List<User> allUser = userService.findAllUser();
        //存放全部结果--所有用户的所有订单，每个用户包含用户名、全部餐品、总价格
        List<BackValue> list = new ArrayList<>();
        List<BackValue> valueList = new ArrayList<>();
        BackValue backValue;

        //全部用户
        for (User user : allUser) {
            //一个用户

            //一个用户的全部订单
            for (String orderNumber : orderInfoService.findAllOrderedByUserId(user.getUserId())) {
                //一个订单为一个BackValue值
                int total = 0;
                backValue = new BackValue();
                backValue.setOrderNumber(orderNumber);
                backValue.setUserName(user.getUserName());
                Map<String,Integer> map = new HashMap<>();
                //一个订单对应的全部餐品
                for (DishExt dishExt : orderInfoService.findAllOrderByUserIdAndOrderNumber(user.getUserId(), orderNumber,1)) {
                    map.put(dishExt.getDish().getDishName(),dishExt.getDish().getPrice());
                    total += dishExt.getDish().getPrice();
                }
                backValue.setDishMap(map);
                backValue.setTotal(total);
                list.add(backValue);
            }

        }

        //分页
        int allCount = (int) Math.ceil(list.size() * 1.0 / Page.PAGE_NUMBER);
        request.setAttribute("allCount",allCount);
        request.setAttribute("preIndex",pageIndex > 1 ? (pageIndex - 1) : 1);
        request.setAttribute("nextIndex", pageIndex < allCount ? (pageIndex + 1) : allCount);

        for (int i = 0,j = (pageIndex-1) * Page.PAGE_NUMBER; i < Page.PAGE_NUMBER; i++,j++){
            if (j >= list.size()){
                continue;
            }
            valueList.add(list.get(j));
        }

        request.setAttribute("allUserOrderNumberDish",valueList);

        /*//页面302问题无效
        response.setHeader("Location", request.getContextPath() + "/order?action=findAllOrder");*/

        request.getRequestDispatcher("view/userorderingdetail.jsp").forward(request,response);
        //return;
    }

    /**
     * 删除点餐车中选中的餐品
     * @param request
     * @param response
     */
    private void deleteSelectedOrderInCar(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("currentUser");
        String dishIds = request.getParameter("dishIds");
        String orderNumber = (String) request.getSession().getAttribute("currentOrderNumber");

        if(dishIds == null || orderNumber == null){
            return;
        }

        String[] dishs = dishIds.split(",");
        if(dishs.length == 0){
            return;
        }

        List<String> list = Arrays.asList(dishs);
        orderInfoService.batchDelSelectedDishInCar(user.getUserId(),orderNumber,list);

        try {
            request.getRequestDispatcher("order?action=orderCar").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 提交点餐车
     * @param request
     * @param response
     */
    private void batchOrderCarCommit(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("currentUser");
        String dishIds = request.getParameter("dishIds");
        String orderNumber = (String) request.getSession().getAttribute("currentOrderNumber");

        if(dishIds == null || orderNumber == null){
            return;
        }

        String[] dishs = dishIds.split(",");
        if(dishs.length == 0){
            return;
        }

        List<String> list = Arrays.asList(dishs);
        orderInfoService.batchUpdate(user.getUserId(),orderNumber,list);
        try {
            request.getRequestDispatcher("order?action=orderCar").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询点餐车
     * @param request
     * @param response
     */
    private void findOrderCar(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("currentUser");
        String orderNumber = (String) request.getSession().getAttribute("currentOrderNumber");

        request.removeAttribute("noOrderCarError");
        if (orderNumber == null || orderInfoService.findAllOrderByUserIdAndOrderNumber(user.getUserId(), orderNumber,0).size() == 0){
            try {
                request.setAttribute("noOrderCarError",true);
                request.getRequestDispatcher("dish?action=generalUserIndex").forward(request,response);
                return;
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        request.setAttribute(
                "currentOrderCar",
                orderInfoService.findAllOrderByUserIdAndOrderNumber(user.getUserId(), orderNumber,0)
        );
        request.removeAttribute("noOrderCarError");
        try {
            request.getRequestDispatcher("view/ordercar.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 批量增加点餐信息--加入点餐车
     * @param request
     * @param response
     */
    private void addBatchOrderInfo(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("currentUser");
        //增加时判断当前是否已有订单号
        String orderNumber = (String) request.getSession().getAttribute("currentOrderNumber");

        String dishIds = request.getParameter("dishIds");

        request.removeAttribute("noSelect");
        if(dishIds == null || dishIds == ""){
            try {
                request.setAttribute("noSelect",true);
                request.getRequestDispatcher("dish?action=generalUserIndex").forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }

        String[] dishs = dishIds.split(",");
        if(dishs.length == 0){
            return;
        } else {
            List<String> list = Arrays.asList(dishs);
            //若当前没有订单号则生成一个
            if ( orderNumber == null ){
                orderNumber = Utils.randomNum(11);
                request.getSession().setAttribute("currentOrderNumber",orderNumber);
            }

            orderInfoService.addByBatchNoCommit(user.getUserId(),list,orderNumber);
        }
        try {
            request.getRequestDispatcher("dish?action=generalUserIndex").forward(request,response);

        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
