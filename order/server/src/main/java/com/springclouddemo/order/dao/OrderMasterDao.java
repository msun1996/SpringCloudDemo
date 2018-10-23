package com.springclouddemo.order.dao;

import com.springclouddemo.order.domain.OrderMaster;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 订单
 * author: mSun
 * date: 2018/10/15
 */
@Mapper
@Repository
public interface OrderMasterDao {
    void save(OrderMaster orderMaster);
    void update(OrderMaster orderMaster);
    OrderMaster findById(String orderId);
}
