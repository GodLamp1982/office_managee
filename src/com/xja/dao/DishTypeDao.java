package com.xja.dao;

import com.xja.bean.DishType;

import java.sql.SQLException;
import java.util.List;

/**
 * @author GodLamp
 * @date 2022/8/13 15:38
 */
public interface DishTypeDao {
    /**
     * 返回所有菜品类别
     * @return
     * @throws SQLException
     */
    List<DishType> findAllType() throws SQLException;

    /**
     * 更新菜品类型信息
     * @param dishType
     * @return
     * @throws SQLException
     */
    int update(DishType dishType) throws SQLException;

    /**
     * 删除类型信息
     * @param typeId
     * @return
     * @throws SQLException
     */
    int del(int typeId) throws SQLException;

    /**
     * 增加类型信息
     * @param typeName
     * @return
     * @throws SQLException
     */
    int add(String typeName) throws SQLException;

    /**
     * 返回一个类型有多少餐品
     * @param typeId
     * @return
     * @throws SQLException
     */
    int allDishInOneTypeCount(int typeId) throws SQLException;
}
