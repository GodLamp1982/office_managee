package com.xja.common;

import com.xja.bean.DishExt;
import com.xja.bean.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author GodLamp
 * @date 2022/8/17 13:48
 */
@Setter
@Getter
@ToString
public class ReturnValue {
    private Page page;

    private List<User> userList;
    private List<BackValue> backValueList;
    private List<DishExt> dishExtList;
}
