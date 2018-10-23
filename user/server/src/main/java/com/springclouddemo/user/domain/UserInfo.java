package com.springclouddemo.user.domain;

import lombok.Data;

/**
 * author: mSun
 * date: 2018/10/22
 */
@Data
public class UserInfo {

    private String id;

    private String username;

    private String password;

    private String openid;

    private Integer role;
}
