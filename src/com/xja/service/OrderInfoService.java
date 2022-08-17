package com.xja.service;

import com.xja.bean.DishExt;
import com.xja.bean.User;
import com.xja.common.BackValue;
import com.xja.common.Page;
import com.xja.common.ReturnValue;

import java.util.List;

/**
 * @author GodLamp
 * @date 2022/8/13 16:46
 */
public interface OrderInfoService {
    /**
     * 增加点餐信息（用户在点餐中）
     * @param userId
     * @param dishIds
     * @param orderNumber
     * @return
     */
    String addByBatchNoCommit(int userId, String dishIds, String orderNumber);

    /**
     * 根据userId与订单号查询所有点的餐
     * @param userId
     * @param orderNumber
     * @param state
     * @return
     */
    List<DishExt> findAllOrderByUserIdAndOrderNumber(int userId, String orderNumber, int state);

    /**
     * 批量更新--提交点餐车
     * @param orderNumber
     */
    void batchUpdate(User user, String orderNumber, String dishIds);

    /**
     * 批量删除点餐的餐品
     * @param userId
     * @param orderNumber
     */
    void batchDelSelectedDishInCar(int userId, String orderNumber, String dishIds);

    /**
     * 返回当前所有的全部订单号
     * @param userId
     * @return
     */
    List<String> findAllOrderedByUserId(int userId);

    /**
     * 获取全部订单
     * @param currentPage

     * @return
     */
    ReturnValue returnAllOrderInfoByPaging(String currentPage);
}
