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
    private String userName;
    private Map<String,Integer> dishMap;
    private int total;
    private String orderNumber;
}
