package com.xja.test;

import com.xja.bean.DishExt;
import com.xja.util.MD5Util;
import org.junit.Test;

/**
 * @author GodLamp
 * @date 2022/8/13 10:21
 */
public class OterTest {

    @Test
    public void test01(){
        System.out.println(MD5Util.getMd5Str("asd"));
        String sql = "Hjcja hasc hoka and ihdoia and";
        System.out.println(sql.length());
        System.out.println(sql.lastIndexOf("and"));
        System.out.println(sql.substring(0, sql.length() - 4));
        System.out.println(Integer.parseInt("-4"));

    }

    @Test
    public void test02(){
        String us = "userAccount45hi45556";
        System.out.println(us.substring(11));
    }

}
