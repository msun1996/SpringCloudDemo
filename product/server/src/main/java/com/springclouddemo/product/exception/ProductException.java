package com.springclouddemo.product.exception;

import com.springclouddemo.product.enums.ResultEnum;

/**
 * author: mSun
 * date: 2018/10/17
 */
public class ProductException extends RuntimeException {

    private Integer code;

    public ProductException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ProductException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

}
