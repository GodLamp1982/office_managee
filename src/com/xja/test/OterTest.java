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
        System.out.println(MD5Util.getMd5Str("qwe"));
    }
}
