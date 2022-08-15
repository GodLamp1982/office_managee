package com.xja.util;


import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
/**
 * @author GodLamp
 * @date 2022/8/9 10:58
 */
public class DBUtil {
    /**
     * 实例化ThreadLocal对象，通过该对象保存连接--线程
     */
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    /**
     * 数据源
     */
    private static DataSource ds;

    /**
     * 私有化构造函数
     */
    private DBUtil() {
    }

    /**
     * 静态代码块
     * 加载数据库驱动（只加载一次）
     */
    static {
        Properties prop = new Properties();
        //方式一：
        //InputStream is = DBUtil.class.getResourceAsStream("/jdbc.properties");
        //方式二：
        InputStream is = DBUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");

        try {
            prop.load(is);
            ds = BasicDataSourceFactory.createDataSource(prop);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭流对象
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取Connection对象连接
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        //从当前线程为key的ThreadLocal获取一个连接
        Connection conn = threadLocal.get();
        //判断连接对象是否有效
        if(conn == null || conn.isClosed()){
            //重新获取连接（从连接池中获取连接）
            conn = ds.getConnection();
            //将创建的连接对象保存到ThreadLocal中
            threadLocal.set(conn);
        }
        return conn;
    }

    /**
     * 回滚
     */
    public static void rollBack(){
        Connection conn = threadLocal.get();
        try {
            //判断连接对象是否有效
            if(conn != null && !conn.isClosed()) {
                //执行事务回滚操作
                conn.rollback();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 关闭连接
     * 将连接对象归还给连接池
     */
    public static void close(){
        //从ThreadLocal获取连接
        Connection conn = threadLocal.get();
        try {
            //判断连接是否有效
            if(conn != null && !conn.isClosed()){
                //关闭连接
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //移除ThreadLocal的所有对象
        threadLocal.remove();
    }

}
