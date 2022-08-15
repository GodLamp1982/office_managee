package com.xja.dao;

import com.xja.bean.Dish;
import com.xja.bean.DishExt;

import java.sql.SQLException;
import java.util.List;

/**
 * @author GodLamp
 * @date 2022/8/13 10:26
 */
public interface DishDao {
    /**
     * 按照备注（remark）查找菜品
     * remark（方法参数的值）
     * -4-查询所有菜品；
     * -3-查询热点菜品
     *
     * 1、2、3、4、..代表按菜品类型查询
     *
     * remark（数据库中字段值）:
     * 0-无 ；
     * -1-今日特价；
     * -2-厨师推荐
     *
     * @param remark
     * @return
     * @throws SQLException
     */
    List<DishExt> findAllDishByRemark(int remark,int pageIndex) throws SQLException;

    /**
     * 按照菜品id查询
     * @param dsihId
     * @return
     * @throws SQLException
     */
    DishExt findByDishId(int dsihId) throws SQLException;

    /**
     * 修改菜品信息
     * @param dish
     * @return
     * @throws SQLException
     */
    int update(Dish dish) throws SQLException;

    /**
     * 删除指定id的菜品
     * @param dishId
     * @return
     * @throws SQLException
     */
    int delDish(int dishId) throws SQLException;

    /**
     * 增加菜品
     * @param dish
     * @return
     * @throws SQLException
     */
    int add(Dish dish) throws SQLException;

    /**
     * 点击率加一
     * @param dishId
     * @return
     * @throws SQLException
     */
    int clickRoteDishAddOne(int dishId) throws SQLException;
}
