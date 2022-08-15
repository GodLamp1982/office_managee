package com.xja.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author GodLamp
 * @date 2022/8/13 10:06
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderInfo {
    private int userId;
    private int dishId;
    private int state;
    private String orderNumber;
}
