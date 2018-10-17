package com.springclouddemo.product.enums;

import lombok.Getter;

/**
 * s商品上下架状态
 * author: mSun
 * date: 2018/10/15
 */
@Getter
public enum  ProductStatusEnum {
    UP(0, "在架"),
    DOWN(1, "下架"),
    ;

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
