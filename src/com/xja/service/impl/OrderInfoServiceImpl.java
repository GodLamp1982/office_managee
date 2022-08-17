package com.xja.service.impl;

import com.xja.bean.DishExt;
import com.xja.bean.User;
import com.xja.common.BackValue;
import com.xja.common.Page;
import com.xja.common.ReturnValue;
import com.xja.dao.impl.OrderInfoDaoImpl;
import com.xja.service.OrderInfoService;
import com.xja.util.DBUtil;
import com.xja.util.Utils;

import java.sql.SQLException;
import java.util.*;

/**
 * @author GodLamp
 * @date 2022/8/13 16:46
 */
public class OrderInfoServiceImpl implements OrderInfoService {
    OrderInfoDaoImpl orderInfoDao = new OrderInfoDaoImpl();
    DishServiceImpl dishService = new DishServiceImpl();
    UserServiceImpl userService = new UserServiceImpl();

    /**
     * 增加点餐信息（用户在点餐中）
     * @param userId
     * @param dishIds
     * @param orderNumber
     * @return
     */
    @Override
    public String addByBatchNoCommit(int userId, String dishIds, String orderNumber){
        String[] dishs = dishIds.split(",");
        //若当前没有订单号则生成一个
        if ( orderNumber == null || orderNumber == ""){
            orderNumber = Utils.randomNum(11);
        }

        if(dishs.length == 0){
            return orderNumber;
        } else {
            List<String> list = Arrays.asList(dishs);
            try {
                orderInfoDao.addByBatchNoCommit(userId, list, orderNumber);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBUtil.close();
            }
            //点击率
            for(int i = 0; i < list.size(); i++){
                dishService.clickRoteDishAddOne(Integer.parseInt(list.get(i)));
            }
        }

        return orderNumber;
    }

    /**
     * 根据userId与订单号查询所有点的餐
     * @param userId
     * @param orderNumber
     * @param state
     * @return
     */
    @Override
    public List<DishExt> findAllOrderByUserIdAndOrderNumber(int userId,String orderNumber,int state){
        List<DishExt> dishExtList = new ArrayList<>();
        try {
            for (Integer dishId : orderInfoDao.findAllOrderByUserIdAndOrderNumber(userId, orderNumber,state)) {
                dishExtList.add(dishService.findByDishId(dishId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close();
        }
        return dishExtList;
    }

    /**
     * 批量更新--提交点餐车
     * @param orderNumber
     */
    @Override
    public void batchUpdate(User user, String orderNumber, String dishIds){

        if(dishIds == null || orderNumber == null || user == null){
            return;
        }

        String[] dishs = dishIds.split(",");
        if(dishs.length == 0){
            return;
        }

        List<String> list = Arrays.asList(dishs);

        for (String dishId : list) {
            try {
                orderInfoDao.update(user.getUserId(), orderNumber, dishId);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBUtil.close();
            }
        }
    }

    /**
     * 批量删除点餐的餐品
     * @param userId
     * @param orderNumber
     */
    @Override
    public void batchDelSelectedDishInCar(int userId, String orderNumber, String dishIds){
        String[] dishs = dishIds.split(",");

        if(dishIds == null || orderNumber == null){
            return;
        }

        List<String> list = Arrays.asList(dishs);
        if(dishs.length == 0){
            return;
        }

        for (String dishId : list) {
            try {
                orderInfoDao.delSelectedDishInCar(userId, orderNumber, dishId);
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBUtil.close();
            }
        }
    }

    /**
     * 返回所有用户的全部订单号
     * @param userId
     * @return
     */
    @Override
    public List<String> findAllOrderedByUserId(int userId){
        try {
            return orderInfoDao.findAllOrderedByUserId(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close();
        }
        return null;
    }

    /**
     * 获取全部订单
     * @param currentPage
     * @return
     */
    @Override
    public ReturnValue returnAllOrderInfoByPaging(String currentPage){
        ReturnValue returnValue = new ReturnValue();

        int pageIndex;
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
        Page page = null;

        //全部用户
        for (User user : allUser) {
            //一个用户

            //一个用户的全部订单
            for (String orderNumber : findAllOrderedByUserId(user.getUserId())) {
                //一个订单为一个BackValue值
                int total = 0;
                backValue = new BackValue();
                backValue.setOrderNumber(orderNumber);
                backValue.setUserName(user.getUserName());
                Map<String,Integer> map = new HashMap<>();
                //一个订单对应的全部餐品
                for (DishExt dishExt : findAllOrderByUserIdAndOrderNumber(user.getUserId(), orderNumber,1)) {
                    map.put(dishExt.getDish().getDishName(),dishExt.getDish().getPrice());
                    total += dishExt.getDish().getPrice();
                }
                backValue.setDishMap(map);
                backValue.setTotal(total);
                list.add(backValue);
            }
        }
        page = new Page(list.size(),pageIndex);

        for (int i = 0,j = (pageIndex-1) * Page.PAGE_NUMBER; i < Page.PAGE_NUMBER; i++,j++){
            if (j >= list.size()){
                continue;
            }
            valueList.add(list.get(j));
        }

        returnValue.setBackValueList(valueList);
        returnValue.setPage(page);
        return returnValue;
    }


}
