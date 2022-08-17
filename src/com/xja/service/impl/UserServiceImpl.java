package com.xja.service.impl;

import com.xja.bean.User;
import com.xja.common.Page;
import com.xja.common.ReturnValue;
import com.xja.dao.impl.UserDaoImpl;
import com.xja.service.UserService;
import com.xja.util.DBUtil;
import com.xja.util.MD5Util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author GodLamp
 * @date 2022/8/13 13:29
 */
public class UserServiceImpl implements UserService {
    UserDaoImpl userDao = new UserDaoImpl();

    /**
     * 根据账号查询用户-判断是否存在
     * @param account
     * @return
     */
    @Override
    public String findByAccount(String account){
        if (account == null || account == ""){
            return null;
        }
        User user = null;
        try {
            user = userDao.findByAccount(account);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close();
        }
        String result = "false";
        if (user != null){
            result = "true";
        }

        return result;
    }

    /**
     * 根据账号查询用户-返回整个用户信息
     * @param account
     * @return
     */
    @Override
    public User findUserByAccount(String account){
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
    public int update(Map<String,String> map,User user){
        if (user == null || map.get("userId") == null || map.get("pwd") == null){
            return -1;
        }

        if ( !user.getPassword().equals(map.get("pwd"))){
            map.put("pwd",MD5Util.getMd5Str(map.get("pwd")));
        }

        try {
            return userDao.update(
                    new User(
                            Integer.parseInt(map.get("userId")),
                            map.get("userName"),
                            map.get("account"),
                            map.get("pwd"),
                            map.get("tel"),
                            map.get("address"),
                            Integer.parseInt(map.get("power"))
                    )
            );
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
     * 查询所有用户
     * @param pageIndex
     * @return
     */
    @Override
    public ReturnValue findAllUserByPaging(int pageIndex){
        List<User> allUser = findAllUser();
        List<User> list = new ArrayList<>();
        ReturnValue returnValue = new ReturnValue();

        Page page = new Page(allUser.size(),pageIndex);

        for (int i = ( (pageIndex - 1) * Page.PAGE_NUMBER ), j = 0; j < 4; j++,i++ ){
            if (i >= allUser.size()){
                continue;
            }
            list.add(allUser.get(i));
        }
        returnValue.setUserList(list);
        returnValue.setPage(page);
        return returnValue;
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
     * @param account
     * @param newPwd
     * @param userName
     * @param tel
     * @return
     */
    @Override
    public int setNewPassword(String account,String newPwd,String userName,String tel){
        User corrUser = findUserByAccount(account);

        if (account == null || account == "" || corrUser == null || newPwd == null || newPwd == "" || userName == null || userName == "" || tel == null || tel == ""){
            return -1;
        }

        if (corrUser.getUserName().equals(userName) &&
                corrUser.getTel().equals(tel)
        ){
            try {
                userDao.setNewPassword(corrUser.getUserId(),MD5Util.getMd5Str(newPwd));
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBUtil.close();
            }
            return 1;
        } else {
            return 2;
        }

    }

}
