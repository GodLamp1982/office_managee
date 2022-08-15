package com.xja.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author GodLamp
 * @date 2022/8/13 10:20
 */
@NoArgsConstructor
@Data
public class DishExt{
    private Dish dish;
    private String typeName;

    public DishExt(Dish dish, String typeName) {
        this.dish = dish;
        this.typeName = typeName;
    }
}
