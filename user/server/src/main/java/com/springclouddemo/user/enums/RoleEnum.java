package com.springclouddemo.user.enums;

import lombok.Getter;

/**
 * author: mSun
 * date: 2018/10/22
 */
@Getter
public enum RoleEnum {
    BUYER(1, "买家"),
    SELLER(2, "卖家"),
    ;

    private Integer code;
    private String messge;

    RoleEnum(Integer code, String messge) {
        this.code = code;
        this.messge = messge;
    }
}
