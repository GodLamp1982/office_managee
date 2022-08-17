package com.xja.service;

import com.xja.bean.User;
import com.xja.common.Page;
import com.xja.common.ReturnValue;

import java.util.List;
import java.util.Map;

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
    String findByAccount(String account);

    /**
     * 根据账号查询用户-返回整个用户信息
     * @param account
     * @return
     */
    User findUserByAccount(String account);

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
    int update(Map<String,String> map, User user);

    /**
     * 返回全部普通账号--管理员无法管理管理员
     * @return
     */
    List<User> findAllUser();

    /**
     * 查询所有用户
     * @param pageIndex
     * @return
     */
    ReturnValue findAllUserByPaging(int pageIndex);

    /**
     * 根据userId删除账号（管理员帐号无法被删除）
     * @param userId
     * @return
     */
    int delByUserId(int userId);

    /**
     * 设置新密码
     * @param account
     * @param newPwd
     * @param userName
     * @param tel
     * @return
     */
    int setNewPassword(String account,String newPwd,String userName,String tel);
}
