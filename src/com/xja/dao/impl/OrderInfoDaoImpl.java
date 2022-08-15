package com.xja.dao.impl;

import com.xja.dao.OrderInfoDao;
import com.xja.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author GodLamp
 * @date 2022/8/13 16:45
 */
public class OrderInfoDaoImpl implements OrderInfoDao {

    /**
     * 增加点餐信息（用户在点餐中）
     * @param userId
     * @param dishIds
     * @return 返回值包含订单号
     * @throws SQLException
     */
    @Override
    public void addByBatchNoCommit(int userId, List<String> dishIds, String orderNumber) throws SQLException {
        String sql = "insert into ac_order_info values (?,?,?,default)";
        Connection conn = DBUtil.getConnection();
        PreparedStatement psm = conn.prepareStatement(sql);

        for (String dishId : dishIds) {
            psm.setInt(1,userId);
            psm.setObject(2,dishId);
            psm.setString(3,orderNumber);
            psm.addBatch();
        }
        psm.executeBatch();
    }

    /**
     * 根据userId与订单号查询所有点的餐
     * @param userId
     * @param orderNumber
     * @return
     * @throws SQLException
     */
    @Override
    public List<Integer> findAllOrderByUserIdAndOrderNumber(int userId, String orderNumber,int state) throws SQLException {
        String sql = "select dishid from ac_order_info where userid=? and ordernumber=? and state=?;";
        Connection conn = DBUtil.getConnection();
        PreparedStatement psm = conn.prepareStatement(sql);
        psm.setInt(1,userId);
        psm.setString(2,orderNumber);
        psm.setInt(3,state);

        ResultSet rs = psm.executeQuery();
        List<Integer> list = new ArrayList<>();
        while (rs.next()){
            list.add(rs.getInt(1));
        }
        return list;
    }

    /**
     * 修改状态--提交点餐车
     * @param userId
     * @param orderNumber
     * @param dishId
     * @return
     * @throws SQLException
     */
    @Override
    public int update(int userId, String orderNumber, String dishId) throws SQLException {
        String sql = "update ac_order_info set state=1 where userid=? and ordernumber=? and dishid=?";
        Connection conn = DBUtil.getConnection();
        PreparedStatement psm = conn.prepareStatement(sql);
        psm.setInt(1,userId);
        psm.setString(2,orderNumber);
        psm.setObject(3,dishId);
        return psm.executeUpdate();
    }

    /**
     * 删除当前用户点的餐
     * @param userId
     * @param orderNumber
     * @param dishId
     * @return
     * @throws SQLException
     */
    @Override
    public int delSelectedDishInCar(int userId,String orderNumber,String dishId) throws SQLException {
        String sql = "delete from ac_order_info where userid=? and ordernumber=? and dishid=? and state=0";
        Connection conn = DBUtil.getConnection();
        PreparedStatement psm = conn.prepareStatement(sql);
        psm.setInt(1,userId);
        psm.setString(2,orderNumber);
        psm.setObject(3,dishId);
        return psm.executeUpdate();
    }

    /**
     * 返回所有用户的全部订单号
     * @param userId
     * @return
     * @throws SQLException
     */
    @Override
    public List<String> findAllOrderedByUserId(int userId) throws SQLException {
        String sql = "select distinct ordernumber from ac_order_info where userid=? and state=1;";
        Connection conn = DBUtil.getConnection();
        PreparedStatement psm = conn.prepareStatement(sql);
        psm.setInt(1,userId);
        ResultSet rs = psm.executeQuery();
        List<String> list = new ArrayList<>();
        while (rs.next()){
            list.add(rs.getString(1));
        }
        return list;
    }

}
