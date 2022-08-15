package com.xja.service.impl;

import com.xja.bean.DishType;
import com.xja.dao.impl.DishTypeDaoImpl;
import com.xja.service.DishTypeService;
import com.xja.util.DBUtil;

import java.sql.SQLException;
import java.util.List;

/**
 * @author GodLamp
 * @date 2022/8/13 15:40
 */
public class DishTypeServiceImpl implements DishTypeService {
    DishTypeDaoImpl dishTypeDao = new DishTypeDaoImpl();

    /**
     * 返回所有菜品类别
     * @return
     */
    @Override
    public List<DishType> findAllType(){
        try {
            return dishTypeDao.findAllType();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close();
        }
        return null;
    }

    /**
     * 更新菜品类型信息
     * @param dishType
     * @return
     */
    @Override
    public int update(DishType dishType){
        try {
            return dishTypeDao.update(dishType);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close();
        }
        return -1;
    }

    /**
     * 删除指定类型
     * @param typeId
     * @return
     */
    @Override
    public int del(int typeId){
        try {
            return dishTypeDao.del(typeId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close();
        }
        return -1;
    }

    /**
     * 增加类型
     * @param typeName
     * @return
     */
    @Override
    public int add(String typeName){
        try {
            return dishTypeDao.add(typeName);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close();
        }
        return -1;
    }

}
