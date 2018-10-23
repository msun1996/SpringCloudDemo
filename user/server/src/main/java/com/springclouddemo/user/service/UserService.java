package com.springclouddemo.user.service;

import com.springclouddemo.user.domain.UserInfo;

/**
 * 用户信息
 * author: mSun
 * date: 2018/10/22
 */
public interface UserService {

    /**
     * 通过用户openid查询用户
     * @param openid
     * @return
     */
    UserInfo findByOpenid(String openid);
}
