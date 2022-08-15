package com.xja.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author GodLamp
 * @date 2022/8/13 10:03
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Dish {
    private int dishId;
    private String dishName;
    private String feature;
    private String ingredients;
    private int price;
    private int typeId;
    private String photo;
    private int clickRote;
    private int remark;
}
