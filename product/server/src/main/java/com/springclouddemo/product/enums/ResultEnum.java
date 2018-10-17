package com.springclouddemo.product.enums;

import com.springclouddemo.product.exception.ProductException;
import lombok.Getter;

/**
 * author: mSun
 * date: 2018/10/17
 */
@Getter
public enum ResultEnum {
    PRODUCT_NOT_EXIST(1, "商品不存在"),
    PRODUCT_STOCK_ERROR(2, "库存有误"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code,String message) {
        this.code = code;
        this.message = message;
    }

}
