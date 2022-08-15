package com.xja.dao.impl;

import com.xja.bean.User;
import com.xja.dao.UserDao;
import com.xja.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author GodLamp
 * @date 2022/8/13 13:28
 */
public class UserDaoImpl implements UserDao {

    /**
     * 根据账号查询用户
     * @param account
     * @return
     * @throws SQLException
     */
    @Override
    public User findByAccount(String account) throws SQLException {
        String sql = "SELECT * FROM ac_user WHERE account=?;";
        Connection conn = DBUtil.getConnection();
        PreparedStatement psm = conn.prepareStatement(sql);
        psm.setString(1,account);
        ResultSet rs = psm.executeQuery();
        if (rs.next()){
            return new User(
                    rs.getInt("userid"),
                    rs.getString("username"),
                    rs.getString("account"),
                    rs.getString("password"),
                    rs.getString("tel"),
                    rs.getString("address"),
                    rs.getInt("power")
            );
        }
        return null;
    }

    /**
     * 增加账号
     * @param user
     * @return
     * @throws SQLException
     */
    @Override
    public int add(User user) throws SQLException {
        String sql = "insert into ac_user values (default,?,?,?,?,?,?)";
        Connection conn = DBUtil.getConnection();
        PreparedStatement psm = conn.prepareStatement(sql);
        psm.setString(1, user.getUserName());
        psm.setString(2, user.getAccount());
        psm.setString(3, user.getPassword());
        psm.setString(4, user.getTel());
        psm.setString(5, user.getAddress());
        psm.setInt(6,user.getPower());
        return psm.executeUpdate();
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     * @throws SQLException
     */
    @Override
    public int update(User user) throws SQLException {
        String sql = "update ac_user set username=?,account=?,password=?,tel=?,address=?,power=? where userid=?";
        Connection conn = DBUtil.getConnection();
        PreparedStatement psm = conn.prepareStatement(sql);
        psm.setString(1, user.getUserName());
        psm.setString(2, user.getAccount());
        psm.setString(3, user.getPassword());
        psm.setString(4, user.getTel());
        psm.setString(5, user.getAddress());
        psm.setInt(6, user.getPower());
        psm.setInt(7, user.getUserId());
        return psm.executeUpdate();
    }

    /**
     * 返回全部普通账号--管理员无法管理管理员
     * @return
     * @throws SQLException
     */
    @Override
    public List<User> findAllUser() throws SQLException {
        String sql = "select * from ac_user where power=0";
        Connection conn = DBUtil.getConnection();
        PreparedStatement psm = conn.prepareStatement(sql);
        ResultSet rs = psm.executeQuery();
        List<User> list = new ArrayList<>();
        while (rs.next()){
            list.add(new User(
                    rs.getInt("userid"),
                    rs.getString("username"),
                    rs.getString("account"),
                    rs.getString("password"),
                    rs.getString("tel"),
                    rs.getString("address"),
                    0
            ));
        }
        return list;
    }

    /**
     * 根据userId删除账号（管理员帐号无法被删除）
     * @param userId
     * @return
     * @throws SQLException
     */
    @Override
    public int delByUserId(int userId) throws SQLException {
        String sql = "delete from ac_user where userid=? and power=0";
        Connection conn = DBUtil.getConnection();
        PreparedStatement psm = conn.prepareStatement(sql);
        psm.setInt(1,userId);
        return psm.executeUpdate();
    }

    /**
     * 更新密码
     * @param userId
     * @param newPwd
     * @return
     * @throws SQLException
     */
    @Override
    public int setNewPassword(int userId,String newPwd) throws SQLException {
        String sql = "update ac_user set password=? where userid=?";
        Connection conn = DBUtil.getConnection();
        PreparedStatement psm = conn.prepareStatement(sql);
        psm.setString(1,newPwd);
        psm.setInt(2,userId);
        return psm.executeUpdate();
    }

}
