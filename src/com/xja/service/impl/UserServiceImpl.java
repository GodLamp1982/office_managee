package com.xja.service.impl;

import com.xja.bean.User;
import com.xja.dao.impl.UserDaoImpl;
import com.xja.service.UserService;
import com.xja.util.DBUtil;
import com.xja.util.MD5Util;

import java.sql.SQLException;
import java.util.List;

/**
 * @author GodLamp
 * @date 2022/8/13 13:29
 */
public class UserServiceImpl implements UserService {
    UserDaoImpl userDao = new UserDaoImpl();

    /**
     * 根据账号查询用户
     * @param account
     * @return
     */
    @Override
    public User findByAccount(String account){
        try {
            return userDao.findByAccount(account);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close();
        }
        return null;
    }

    /**
     * 增加账户
     * @param user
     * @return
     */
    @Override
    public int addAccount(User user){
        user.setPassword(MD5Util.getMd5Str(user.getPassword()));
        try {
            return userDao.add(user);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close();
        }
        return -1;
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @Override
    public int update(User user){
        user.setPassword(MD5Util.getMd5Str(user.getPassword()));
        try {
            return userDao.update(user);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close();
        }
        return -1;
    }

    /**
     * 返回全部普通账号--管理员无法管理管理员
     * @return
     */
    @Override
    public List<User> findAllUser(){
        try {
            return userDao.findAllUser();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close();
        }
        return null;
    }

    /**
     * 根据userId删除账号（管理员帐号无法被删除）
     * @param userId
     * @return
     */
    @Override
    public int delByUserId(int userId){
        try {
            return userDao.delByUserId(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close();
        }
        return -1;
    }

    /**
     * 设置新密码
     * @param userId
     * @param newPwd
     * @return
     */
    @Override
    public int setNewPassword(int userId,String newPwd){
        try {
            return userDao.setNewPassword(userId,MD5Util.getMd5Str(newPwd));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close();
        }
        return -1;
    }

}
