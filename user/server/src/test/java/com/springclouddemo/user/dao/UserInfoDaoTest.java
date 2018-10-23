package com.springclouddemo.user.dao;

import com.springclouddemo.user.UserApplicationTests;
import com.springclouddemo.user.domain.UserInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.junit.Assert.*;

@Component
public class UserInfoDaoTest extends UserApplicationTests {

    @Autowired
    private UserInfoDao userInfoDao;

    @Test
    public void findByOpenid() {
        UserInfo userInfo = userInfoDao.findByOpenid("11");
    }
}