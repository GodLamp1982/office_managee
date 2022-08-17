package com.xja.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author GodLamp
 * @date 2022/8/16 9:21
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Condition {
    //查询条件
    //关键字
    private String title;
    //最低价格
    private int begin;
    //最高价格
    private int end;
}
