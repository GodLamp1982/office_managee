package com.xja.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * @author GodLamp
 * @date 2022/8/13 16:45
 */
public interface OrderInfoDao {
    /**
     * 增加点餐信息（用户在点餐中）
     * @param userId
     * @param dishIds
     * @return 返回值包含订单号
     * @throws SQLException
     */
    void addByBatchNoCommit(int userId, List<String> dishIds, String orderNumber) throws SQLException;

    /**
     * 根据userId与订单号查询所有点的餐
     * @param userId
     * @param orderNumber
     * @return
     * @throws SQLException
     */
    List<Integer> findAllOrderByUserIdAndOrderNumber(int userId, String orderNumber,int state) throws SQLException;

    /**
     * 修改状态--提交点餐车
     * @param userId
     * @param orderNumber
     * @param dishId
     * @return
     * @throws SQLException
     */
    int update(int userId, String orderNumber, String dishId) throws SQLException;

    /**
     * 删除当前用户点的餐
     * @param userId
     * @param orderNumber
     * @param dishId
     * @return
     * @throws SQLException
     */
    int delSelectedDishInCar(int userId, String orderNumber, String dishId) throws SQLException;

    /**
     * 返回所有用户的全部订单号
     * @param userId
     * @return
     * @throws SQLException
     */
    List<String> findAllOrderedByUserId(int userId) throws SQLException;
}
