package com.xja.service;

import com.xja.bean.DishExt;

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
     */
    void addByBatchNoCommit(int userId, List<String> dishIds, String orderNumber);

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
     * @param userId
     * @param orderNumber
     * @param list
     */
    void batchUpdate(int userId, String orderNumber, List<String> list);

    /**
     * 返回当前所有的全部订单号
     * @param userId
     * @return
     */
    List<String> findAllOrderedByUserId(int userId);
}
