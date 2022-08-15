package com.xja.service.impl;

import com.xja.bean.DishExt;
import com.xja.dao.impl.OrderInfoDaoImpl;
import com.xja.service.OrderInfoService;
import com.xja.util.DBUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author GodLamp
 * @date 2022/8/13 16:46
 */
public class OrderInfoServiceImpl implements OrderInfoService {
    OrderInfoDaoImpl orderInfoDao = new OrderInfoDaoImpl();
    DishServiceImpl dishService = new DishServiceImpl();

    /**
     * 增加点餐信息（用户在点餐中）
     * @param userId
     * @param dishIds
     * @param orderNumber
     */
    @Override
    public void addByBatchNoCommit(int userId, List<String> dishIds, String orderNumber){
        //点击率
        for(int i = 0; i < dishIds.size(); i++){
            dishService.clickRoteDishAddOne(Integer.parseInt(dishIds.get(i)));
        }
        try {
            orderInfoDao.addByBatchNoCommit(userId, dishIds, orderNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close();
        }
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
     * @param userId
     * @param orderNumber
     * @param list
     */
    @Override
    public void batchUpdate(int userId, String orderNumber, List<String> list){
        for (String dishId : list) {
            try {
                orderInfoDao.update(userId, orderNumber, dishId);
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
     * @param dishList
     */
    public void batchDelSelectedDishInCar(int userId,String orderNumber,List<String> dishList){
        for (String dishId : dishList) {
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

}
