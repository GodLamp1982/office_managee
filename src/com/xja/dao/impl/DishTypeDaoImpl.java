package com.xja.dao.impl;

import com.xja.bean.DishType;
import com.xja.dao.DishTypeDao;
import com.xja.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author GodLamp
 * @date 2022/8/13 15:39
 */
public class DishTypeDaoImpl implements DishTypeDao {

    /**
     * 返回所有菜品类别
     * @return
     * @throws SQLException
     */
    @Override
    public List<DishType> findAllType() throws SQLException {
        String sql = "select * from ac_dish_type";
        Connection conn = DBUtil.getConnection();
        PreparedStatement psm = conn.prepareStatement(sql);
        ResultSet rs = psm.executeQuery();
        List<DishType> list = new ArrayList<>();
        while (rs.next()){
            list.add(new DishType(
                    rs.getInt("typeid"),
                    rs.getString("typename")
            ));
        }
        return list;
    }

    /**
     * 更新菜品类型信息
     * @param dishType
     * @return
     * @throws SQLException
     */
    @Override
    public int update(DishType dishType) throws SQLException {
        String sql = "update ac_dish_type set typename=? where typeid=?";
        Connection conn = DBUtil.getConnection();
        PreparedStatement psm = conn.prepareStatement(sql);
        psm.setString(1,dishType.getTypeName());
        psm.setInt(2,dishType.getTypeId());
        return psm.executeUpdate();
    }

    /**
     * 删除类型信息
     * @param typeId
     * @return
     * @throws SQLException
     */
    @Override
    public int del(int typeId) throws SQLException {
        String sql = "delete from ac_dish_type where typeid=?";
        Connection conn = DBUtil.getConnection();
        PreparedStatement psm = conn.prepareStatement(sql);
        psm.setInt(1,typeId);
        return psm.executeUpdate();
    }

    /**
     * 增加类型信息
     * @param typeName
     * @return
     * @throws SQLException
     */
    @Override
    public int add(String typeName) throws SQLException {
        String sql = "insert into ac_dish_type values (default,?)";
        Connection conn = DBUtil.getConnection();
        PreparedStatement psm = conn.prepareStatement(sql);
        psm.setString(1,typeName);
        return psm.executeUpdate();
    }

}
