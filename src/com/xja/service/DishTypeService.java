package com.xja.service;

import com.xja.bean.DishType;

import java.util.List;

/**
 * @author GodLamp
 * @date 2022/8/13 15:39
 */
public interface DishTypeService {
    /**
     * 返回所有菜品类别
     * @return
     */
    List<DishType> findAllType();

    /**
     * 更新菜品类型信息
     * @param dishType
     * @return
     */
    int update(DishType dishType);

    /**
     * 删除指定类型
     * @param typeId
     * @return
     */
    int del(int typeId);

    /**
     * 增加类型
     * @param typeName
     * @return
     */
    int add(String typeName);

    /**
     * 返回一个类型有多少菜品
     * @param typeId
     * @return
     */
    int allDishInOneTypeCount(int typeId);
}
