package com.xja.common;

import com.xja.util.DBUtil;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dao {
    /**
     * 查询所有数据列表
     * @param <T>
     * @return
     */
    public <T> List<T> findAll(String tableName,Class<T> clazz){
        List<T> list = new ArrayList<>();
        String sql = "select * from " + tableName;

        try {
            //获取连接对象
            Connection conn = DBUtil.getConnection();
            //获取PreparedStatement对象
            PreparedStatement ps = conn.prepareStatement(sql);

            //执行SQl语句
            ResultSet rs = ps.executeQuery();

            //处理查询结果
            while(rs.next()){
                //实例化一个T类型的对象
                T t = clazz.newInstance();

                //获取t的所有属性
                Field[] fields = clazz.getDeclaredFields();

                //遍历对象t的所有字段
                for(Field field : fields){
                    //获取字段的名称
                    String name = field.getName();
                    //获取字段的类型
                    Class type = field.getType();

                    field.setAccessible(true);
                    //获取rs中的数据
                    switch (type.getSimpleName()){
                        case "String":
                            field.set(t,rs.getString(name));
                            break;
                        case "int":
                            field.set(t,rs.getInt(name));
                            break;
                        case "Date":
                            field.set(t,rs.getDate(name));
                            break;
                    }
                    field.setAccessible(false);
                }

                //将对象添加到list集合
                list.add(t);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        finally {
            DBUtil.close();
        }
        return list;
    }

    /**
     * 查询表的数据总数
     * @param tableName
     * @return
     */
    public int countAllData(String tableName){
        String sql = "select count(1) from " + tableName;
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            PreparedStatement psm = conn.prepareStatement(sql);
            ResultSet rs = psm.executeQuery();
            if (rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

}
