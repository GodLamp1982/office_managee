package com.xja.service.impl;

import com.xja.bean.Dish;
import com.xja.bean.DishExt;
import com.xja.common.Dao;
import com.xja.dao.impl.DishDaoImpl;
import com.xja.service.DishService;
import com.xja.util.DBUtil;

import java.sql.SQLException;
import java.util.List;

/**
 * @author GodLamp
 * @date 2022/8/13 10:59
 */
public class DishServiceImpl implements DishService {
    DishDaoImpl dishDao = new DishDaoImpl();
    Dao dao = new Dao();

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
    @Override
    public List<DishExt> findAllDishByRemark(int remark, int pageIndex){
        try {
            return dishDao.findAllDishByRemark(remark,pageIndex);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close();
        }
        return null;
    }

    /**
     * 按照菜品id查询
     * @param dsihId
     * @return
     */
    @Override
    public DishExt findByDishId(int dsihId){
        try {
            return dishDao.findByDishId(dsihId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close();
        }
        return null;
    }

    /**
     * 修改菜品信息
     * @param dish
     * @return
     */
    @Override
    public int update(Dish dish){
        try {
            return dishDao.update(dish);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close();
        }
        return -1;
    }

    /**
     * 删除指定Id的菜品信息
     * @param dishId
     * @return
     */
    @Override
    public int delDish(int dishId){
        try {
            return dishDao.delDish(dishId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close();
        }
        return -1;
    }

    /**
     * 增加菜品
     * @param dish
     * @return
     */
    @Override
    public int add(Dish dish){
        try {
            return dishDao.add(dish);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close();
        }
        return -1;
    }

    /**
     * 点击率加一
     * @param dishId
     * @return
     */
    @Override
    public int clickRoteDishAddOne(int dishId){
        try {
            return dishDao.clickRoteDishAddOne(dishId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close();
        }
        return -1;
    }

    /**
     * 返回记录总数
     * @return
     */
    @Override
    public int countAll(){
        return dao.countAllData("ac_dish");
    }

}
