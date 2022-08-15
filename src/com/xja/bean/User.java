package com.xja.bean;

import lombok.*;

/**
 * @author GodLamp
 * @date 2022/8/13 9:58
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private int userId;
    private String userName;
    private String account;
    private String password;
    private String tel;
    private String address;
    private int power;
}
