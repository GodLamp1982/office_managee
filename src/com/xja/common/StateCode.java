package com.xja.common;

/**
 * 定义系统状态码集合
 */
public class StateCode {
    //用户注册成功
    public static final int REGISTER_SUCCESS=100;
    //用户注册失败(系统异常)
    public static final int REGISTER_FAILD=101;
    //账号已经存在
    public static final int REGISTER_EXISTS=102;

    //验证成功
    public static final int LOGIN_VERIFY_SUCCESS=200;
    //账号不存在
    public static final int LOGIN_NO_ACCOUNT=201;
    //密码错误
    public static final int LOGIN_PWD_ERROR = 202;
    //账号已被冻结
    public static final int LOGIN_ACCOUNT_LOCK = 203;
    //验证码错误
    public static  final  int  LOGIN_VERIFYCODE_ERROE=204;

    //数据添加成功
    public static final int ADD_DATA_SUCCESS=300;
    //数据添加失败
    public static final int ADD_DATA_FAILD=301;


    private StateCode() {}

}
