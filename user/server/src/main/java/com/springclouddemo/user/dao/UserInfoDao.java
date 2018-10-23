package com.springclouddemo.user.dao;

import com.springclouddemo.user.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 用户信息
 * author: mSun
 * date: 2018/10/22
 */
@Mapper
@Repository
public interface UserInfoDao {

    UserInfo findByOpenid(String openid);
}
