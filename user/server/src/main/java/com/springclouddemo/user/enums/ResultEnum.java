package com.springclouddemo.user.enums;

import lombok.Getter;

/**
 * author: mSun
 * date: 2018/10/22
 */
@Getter
public enum  ResultEnum {
    LOGIN_FAIL(1,"登录失败"),
    ROLE_ERROR(2, "角色权限有误"),
    ;

    Integer code;
    String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
