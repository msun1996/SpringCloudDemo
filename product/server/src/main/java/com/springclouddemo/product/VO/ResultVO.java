package com.springclouddemo.product.VO;

import lombok.Data;

/**
 * http请求返回的最外层对象
 * author: mSun
 * date: 2018/10/15
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
