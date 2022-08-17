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
     * @param typeId
     * @param typeName
     * @return
     */
    @Override
    public int update(String typeId,String typeName){
        if (typeId == null || typeName == null){
            return -1;
        }

        try {
            return dishTypeDao.update(new DishType(
                    Integer.parseInt(typeId),
                    typeName
            ));
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
    public int del(String typeId){
        if (typeId == null || typeId == ""){
            return -1;
        }

        try {
            return dishTypeDao.del(Integer.parseInt(typeId));
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
        if (typeName == null){
            return -1;
        }
        try {
            return dishTypeDao.add(typeName);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close();
        }
        return -1;
    }

    /**
     * 返回一个类型有多少菜品
     * @param typeId
     * @return
     */
    @Override
    public int allDishInOneTypeCount(int typeId){
        try {
            return dishTypeDao.allDishInOneTypeCount(typeId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close();
        }
        return -1;
    }

}
