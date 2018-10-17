package com.springclouddemo.order.enums;

import lombok.Getter;

/**
 * author: mSun
 * date: 2018/10/15
 */
@Getter
public enum OrderStatusEnum {
    NEW(0, "新订单"),
    FINISHED(1, "完结订单"),
    CANCEL(2, "取消订单"),
    ;

    private Integer code;
    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
