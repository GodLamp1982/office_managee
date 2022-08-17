package com.xja.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author GodLamp
 * @date 2022/8/13 17:57
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BackValue {
    //保存一个订单的所有信息
    //用户名
    private String userName;
    //菜名-价格
    private Map<String,Integer> dishMap;
    //总价格
    private int total;
    //订单号
    private String orderNumber;
}
