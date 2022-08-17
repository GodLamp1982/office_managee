package com.xja.service.impl;

import com.xja.bean.Dish;
import com.xja.bean.DishExt;
import com.xja.common.Condition;
import com.xja.common.Dao;
import com.xja.dao.impl.DishDaoImpl;
import com.xja.service.DishService;
import com.xja.util.DBUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author GodLamp
 * @date 2022/8/13 10:59
 */
public class DishServiceImpl implements DishService {
    DishDaoImpl dishDao = new DishDaoImpl();
    Dao dao = new Dao();

    /**
     * 按照备注（remark）查找菜品
     *      * remark（方法参数的值）
     *      * -4-查询所有菜品；
     *      * -3-查询热点菜品
     *      *
     *      * 1、2、3、4、..代表按菜品类型查询
     *      *
     *      * remark（数据库中字段值）:
     *      * 0-无 ；
     *      * -1-今日特价；
     *      * -2-厨师推荐
     * @param remarkS
     * @param currentPage
     * @return
     */
    @Override
    public List<DishExt> findAllDishByRemark(String remarkS, String currentPage){
        int remark;
        if (remarkS == null || remarkS == ""){
            remark = -4;
        } else {
            remark = Integer.parseInt(remarkS);
        }

        int pageIndex;
        if (currentPage == null || currentPage == ""){
            pageIndex = 1;
        } else {
            pageIndex = Integer.parseInt(currentPage);
        }

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
     * 根据分类查询该分类的菜品
     * @param remarkS
     * @return
     */
    @Override
    public List<DishExt> seeDishInfoByType(String remarkS){
        if (remarkS == null || remarkS == ""){
            return null;
        }

        return findAllDishByRemark(remarkS,"1");

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
     * @param map
     * @return
     */
    @Override
    public int update(Map<String,String> map){
        if (map.get("dishIdS") == null || map.get("dishIdS") == ""){
            return -1;
        }

        DishExt oldDish = findByDishId(
                Integer.parseInt(map.get("dishIdS"))
        );

        if (oldDish == null){
            return -1;
        }

        if ("".equals(map.get("fileName"))){
            map.put("fileName",oldDish.getDish().getPhoto());
        }

        try {
            return dishDao.update(new Dish(
                    oldDish.getDish().getDishId(),
                    map.get("dishName"),
                    map.get("feature"),
                    map.get("ingredients"),
                    Integer.parseInt(map.get("price")),
                    Integer.parseInt(map.get("typeId")),
                    map.get("fileName"),
                    oldDish.getDish().getClickRote(),
                    Integer.parseInt(map.get("remark"))
            ));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close();
        }
        return -1;
    }

    /**
     * 删除指定Id的菜品信息
     * @param dishIdS
     * @return
     */
    @Override
    public int delDish(String dishIdS){
        if (dishIdS == null || dishIdS == ""){
            return -1;
        }
        try {
            return dishDao.delDish(Integer.parseInt(dishIdS));
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

    /**
     * 查询某个类型有多少菜品
     * @param typeId
     * @return
     */
    @Override
    public int countAllByTypeId(int typeId){

        String sql = "ac_dish ";
        if (typeId > 0){
            sql += "where typeid=" + typeId;
        }

        return dao.countAllData(sql);
    }

    /**
     * 根据条件查询
     * @param map
     * @return
     */
    @Override
    public List<DishExt> searchDishByCondition(Map<String,String> map){
        int begin = 0;
        int end = 0;
        if (map.get("beginS") != null && map.get("beginS") != ""){
            begin = Integer.parseInt(map.get("beginS"));
        }
        if (map.get("endS") != null && map.get("endS") != ""){
            end = Integer.parseInt(map.get("endS"));
        }

        try {
            return dishDao.searchDishByCondition(new Condition(map.get("title"),begin,end));

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close();
        }
        return null;
    }

}
