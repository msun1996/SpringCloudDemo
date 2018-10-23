package com.springclouddemo.user.service.impl;

import com.springclouddemo.user.dao.UserInfoDao;
import com.springclouddemo.user.domain.UserInfo;
import com.springclouddemo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author: mSun
 * date: 2018/10/22
 */
@Service
public class UserSeriveImpl implements UserService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public UserInfo findByOpenid(String openid) {
        return userInfoDao.findByOpenid(openid);
    }
}
