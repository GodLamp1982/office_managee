package com.xja.dao.impl;

import com.xja.bean.Dish;
import com.xja.bean.DishExt;
import com.xja.common.Condition;
import com.xja.common.Page;
import com.xja.dao.DishDao;
import com.xja.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author GodLamp
 * @date 2022/8/13 10:25
 */
public class DishDaoImpl implements DishDao {

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
    public List<DishExt> findAllDishByRemark(int remark,int pageIndex) throws SQLException {
        List<Object> paramList = new ArrayList<>();

        String sql = "SELECT m.*,a.typename FROM ac_dish m\n" +
                "INNER JOIN ac_dish_type a ON m.typeid=a.typeid";

        if(remark == -1 || remark == -2){
            sql += " WHERE m.remark=?";
            paramList.add(remark);
        }

        if(remark > 0) {
            sql += " WHERE m.typeid=?";
            paramList.add(remark);
        }

        if(remark == -3){
            sql += " order by m.clickrote desc";
        }

        /*if (remark != -4){
            sql += " limit ?,?;";
            paramList.add( (pageIndex - 1) * Page.PAGE_NUMBER);
            paramList.add(Page.PAGE_NUMBER);
        }*/

        sql += " limit ?,?;";
        paramList.add( (pageIndex - 1) * Page.PAGE_NUMBER);
        paramList.add(Page.PAGE_NUMBER);

        Connection conn = DBUtil.getConnection();
        PreparedStatement psm = conn.prepareStatement(sql);
        for(int i = 0; i < paramList.size(); i++){
            psm.setObject((i + 1),paramList.get(i));
        }

        ResultSet rs = psm.executeQuery();
        List<DishExt> list = new ArrayList<>();

        while (rs.next()){
            list.add(new DishExt(
                    new Dish(
                            rs.getInt("dishid"),
                            rs.getString("dishname"),
                            rs.getString("feature"),
                            rs.getString("ingredients"),
                            rs.getInt("price"),
                            rs.getInt("typeid"),
                            rs.getString("photo"),
                            rs.getInt("clickrote"),
                            rs.getInt("remark")
                    ),
                    rs.getString("typename")
            ));
        }
        return list;
    }

    /**
     * 按照菜品id查询
     * @param dsihId
     * @return
     * @throws SQLException
     */
    @Override
    public DishExt findByDishId(int dsihId) throws SQLException {
        String sql = "SELECT m.*,a.typename FROM ac_dish m\n" +
                "INNER JOIN ac_dish_type a ON m.typeid=a.typeid\n" +
                "WHERE m.dishid=?;";
        Connection conn = DBUtil.getConnection();
        PreparedStatement psm = conn.prepareStatement(sql);
        psm.setInt(1,dsihId);
        ResultSet rs =psm.executeQuery();
        if(rs.next()){
            return new DishExt(
                    new Dish(
                            rs.getInt("dishid"),
                            rs.getString("dishname"),
                            rs.getString("feature"),
                            rs.getString("ingredients"),
                            rs.getInt("price"),
                            rs.getInt("typeid"),
                            rs.getString("photo"),
                            rs.getInt("clickrote"),
                            rs.getInt("remark")
                    ),
                    rs.getString("typename")
            );
        }
        return null;
    }

    /**
     * 修改菜品信息
     * @param dish
     * @return
     * @throws SQLException
     */
    @Override
    public int update(Dish dish) throws SQLException {
        String sql = "update ac_dish set dishname=?,feature=?,ingredients=?,price=?,typeid=?,photo=?,remark=? where dishid=?";
        Connection conn = DBUtil.getConnection();
        PreparedStatement psm = conn.prepareStatement(sql);
        psm.setString(1, dish.getDishName());
        psm.setString(2, dish.getFeature());
        psm.setString(3, dish.getIngredients());
        psm.setInt(4,dish.getPrice());
        psm.setInt(5,dish.getTypeId());
        psm.setString(6, dish.getPhoto());
        psm.setInt(7,dish.getRemark());
        psm.setInt(8,dish.getDishId());
        return psm.executeUpdate();
    }

    /**
     * 删除指定id的菜品
     * @param dishId
     * @return
     * @throws SQLException
     */
    @Override
    public int delDish(int dishId) throws SQLException {
        String sql = "delete from ac_dish where dishid=?";
        Connection conn = DBUtil.getConnection();
        PreparedStatement psm = conn.prepareStatement(sql);
        psm.setInt(1,dishId);
        return psm.executeUpdate();
    }

    /**
     * 增加菜品
     * @param dish
     * @return
     * @throws SQLException
     */
    @Override
    public int add(Dish dish) throws SQLException {
        String sql = "insert into ac_dish values (default,?,?,?,?,?,?,default,?)";
        Connection conn = DBUtil.getConnection();
        PreparedStatement psm = conn.prepareStatement(sql);
        psm.setString(1, dish.getDishName());
        psm.setString(2, dish.getFeature());
        psm.setString(3, dish.getIngredients());
        psm.setInt(4,dish.getPrice());
        psm.setInt(5,dish.getTypeId());
        psm.setString(6, dish.getPhoto());
        psm.setInt(7,dish.getRemark());
        return psm.executeUpdate();
    }


    /**
     * 点击率加一
     * @param dishId
     * @return
     * @throws SQLException
     */
    @Override
    public int clickRoteDishAddOne(int dishId) throws SQLException {
        String sql = "UPDATE ac_dish SET clickrote=clickrote+1 WHERE dishid=?;";
        Connection conn = DBUtil.getConnection();
        PreparedStatement psm = conn.prepareStatement(sql);
        psm.setInt(1,dishId);
        return psm.executeUpdate();
    }

    /**
     * 根据条件查询菜品
     * @param condition
     * @return
     * @throws SQLException
     */
    @Override
    public List<DishExt> searchDishByCondition(Condition condition) throws SQLException {
        String sql = "SELECT m.*,a.typename FROM ac_dish m\n" +
                "INNER JOIN ac_dish_type a ON m.typeid=a.typeid";

        if ( (condition.getTitle() != null && condition.getTitle() != "") || condition.getBegin() >0 || condition.getEnd() > 0){
            sql +=" WHERE";
        }

        List<Object> list = new ArrayList<>();

        //根据关键字查询
        if (condition.getTitle() != null && condition.getTitle() != ""){
            sql += " LOCATE(?,m.dishname) OR LOCATE(?,m.feature) OR LOCATE(?,m.ingredients) OR LOCATE(?,a.typename) and";
            for (int i = 0; i < 4; i++){
                list.add(condition.getTitle());
            }
        }

        //根据最低价格查询
        if (condition.getBegin() > 0){
            sql += " m.price >= ? and";
            list.add(condition.getBegin());
        }

        //根据最高价格查询
        if ( condition.getEnd() > 0 && condition.getEnd() >= condition.getBegin()){
            sql += " m.price <= ?";
            list.add(condition.getEnd());
        }

        //去除掉SQL语句末尾多余的 and
        if ( (sql.length() - 3) == (sql.lastIndexOf("and")) ){
            sql = sql.substring(0,sql.length() - 4);
        }

        Connection conn = DBUtil.getConnection();
        PreparedStatement psm = conn.prepareStatement(sql);

        //批量设置 ? 值
        if ( list.size() > 0){
            for (int i = 0; i < list.size(); i++){
                psm.setObject( (i + 1),list.get(i));
            }
        }

        ResultSet rs = psm.executeQuery();

        List<DishExt> dishExtList = new ArrayList<>();
        while (rs.next()){
            dishExtList.add(new DishExt(
                    new Dish(
                            rs.getInt("dishid"),
                            rs.getString("dishname"),
                            rs.getString("feature"),
                            rs.getString("ingredients"),
                            rs.getInt("price"),
                            rs.getInt("typeid"),
                            rs.getString("photo"),
                            rs.getInt("clickrote"),
                            rs.getInt("remark")
                    ),
                    rs.getString("typename")
            ));
        }

        return dishExtList;
    }

}
