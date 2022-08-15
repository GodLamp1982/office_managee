package com.xja.common;

import lombok.*;

/**
 * 返回结果对象
 *  当返回结果较为复杂时可以通过Result进行返回
 */
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Result {
    //状态码
    @NonNull
    private int code;
    //消息内容
    @NonNull
    private String message;
    //数据
    private Object data;
}
