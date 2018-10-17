package com.springclouddemo.order.dao;

import com.springclouddemo.order.OrderApplicationTests;
import com.springclouddemo.order.domain.OrderMaster;
import com.springclouddemo.order.enums.OrderStatusEnum;
import com.springclouddemo.order.enums.PayStatusEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderMasterDaoTest extends OrderApplicationTests {

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Test
    public void save() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("11111111111");
        orderMaster.setBuyerName("a");
        orderMaster.setBuyerPhone("110");
        orderMaster.setBuyerAddress("zhe");
        orderMaster.setBuyerOpenid("110");
        orderMaster.setOrderAmount(new BigDecimal(1111));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterDao.save(orderMaster);
    }
}