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
    private String title;
    private int begin;
    private int end;
}
