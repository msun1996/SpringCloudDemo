package com.springclouddemo.order.exception;

import com.springclouddemo.order.enums.ResultEnum;

/**
 * author: mSun
 * date: 2018/10/15
 */
public class OrderException extends RuntimeException {

    private Integer code;

    public OrderException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public OrderException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
