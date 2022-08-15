package com.xja.service;

import com.xja.bean.User;

import java.util.List;

/**
 * @author GodLamp
 * @date 2022/8/13 13:28
 */
public interface UserService {
    /**
     * 根据账号查询用户
     * @param account
     * @return
     */
    User findByAccount(String account);

    /**
     * 增加账户
     * @param user
     * @return
     */
    int addAccount(User user);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    int update(User user);

    /**
     * 返回全部普通账号--管理员无法管理管理员
     * @return
     */
    List<User> findAllUser();

    /**
     * 根据userId删除账号（管理员帐号无法被删除）
     * @param userId
     * @return
     */
    int delByUserId(int userId);

    /**
     * 设置新密码
     * @param userId
     * @param newPwd
     * @return
     */
    int setNewPassword(int userId, String newPwd);
}
