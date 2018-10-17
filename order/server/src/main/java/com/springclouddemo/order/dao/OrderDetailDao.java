package com.springclouddemo.order.dao;

import com.springclouddemo.order.domain.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 订单详情
 * author: mSun
 * date: 2018/10/15
 */
@Mapper
@Repository
public interface OrderDetailDao {
    void save(OrderDetail orderDetail);
}
