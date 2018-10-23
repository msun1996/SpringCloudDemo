package com.springclouddemo.user.VO;

import lombok.Data;

/**
 * author: mSun
 * date: 2018/10/22
 */
@Data
public class ResultVO<T> {

    // 错误码
    private Integer code;

    // 提示信息
    private String msg;

    // 具体内容
    private T data;

}
