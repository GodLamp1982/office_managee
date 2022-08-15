package com.xja.dao;

import com.xja.bean.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @author GodLamp
 * @date 2022/8/13 13:28
 */
public interface UserDao {
    /**
     * 根据账号查询用户
     * @param account
     * @return
     * @throws SQLException
     */
    User findByAccount(String account) throws SQLException;

    /**
     * 增加账号
     * @param user
     * @return
     * @throws SQLException
     */
    int add(User user) throws SQLException;

    /**
     * 更新用户信息
     * @param user
     * @return
     * @throws SQLException
     */
    int update(User user) throws SQLException;

    /**
     * 返回全部普通账号--管理员无法管理管理员
     * @return
     * @throws SQLException
     */
    List<User> findAllUser() throws SQLException;

    /**
     * 根据userId删除账号（管理员帐号无法被删除）
     * @param userId
     * @return
     * @throws SQLException
     */
    int delByUserId(int userId) throws SQLException;

    /**
     * 更新密码
     * @param userId
     * @param newPwd
     * @return
     * @throws SQLException
     */
    int setNewPassword(int userId, String newPwd) throws SQLException;
}
