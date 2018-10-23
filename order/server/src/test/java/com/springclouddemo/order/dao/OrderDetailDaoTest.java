package com.springclouddemo.order.dao;

import com.springclouddemo.order.OrderApplicationTests;
import com.springclouddemo.order.domain.OrderDetail;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@Component
public class OrderDetailDaoTest extends OrderApplicationTests {

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Test
    public void save() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("111");
        orderDetail.setOrderId("11111");
        orderDetail.setProductIcon("http://xx.com");
        orderDetail.setProductId("157875196366160022");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductPrice(new BigDecimal(0.01));
        orderDetail.setProductQuantity(1);
        orderDetailDao.save(orderDetail);
    }

    @Test
    public void findByOrderId() {
        orderDetailDao.findByOrderId("1111");
    }
}