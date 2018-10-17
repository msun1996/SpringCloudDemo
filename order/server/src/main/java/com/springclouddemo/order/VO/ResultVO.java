package com.springclouddemo.order.VO;

import lombok.Data;

/**
 * author: mSun
 * date: 2018/10/15
 */
@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;
}
