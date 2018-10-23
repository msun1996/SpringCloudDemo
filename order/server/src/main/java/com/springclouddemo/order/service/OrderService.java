package com.springclouddemo.order.service;

import com.springclouddemo.order.DTO.OrderDTO;

/**
 * author: mSun
 * date: 2018/10/15
 */
public interface OrderService {

    // 创建订单
    OrderDTO create(OrderDTO orderDTO);

    // 完结订单(卖家操作)
    OrderDTO finish(String orderId);
}
