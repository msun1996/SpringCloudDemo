package com.springclouddemo.order.service.impl;

import com.springCloudDemo.product.client.ProductClient;
import com.springCloudDemo.product.common.DecreaseStockInput;
import com.springCloudDemo.product.common.ProductInfoOutput;
import com.springclouddemo.order.DTO.OrderDTO;
import com.springclouddemo.order.dao.OrderDetailDao;
import com.springclouddemo.order.dao.OrderMasterDao;
import com.springclouddemo.order.domain.OrderDetail;
import com.springclouddemo.order.domain.OrderMaster;
import com.springclouddemo.order.enums.OrderStatusEnum;
import com.springclouddemo.order.enums.PayStatusEnum;
import com.springclouddemo.order.service.OrderService;
import com.springclouddemo.order.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * author: mSun
 * date: 2018/10/15
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private ProductClient productClient;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        String orderId = KeyUtil.genUniqueKey();
        // 查寻商品信息(调用商品服务)
        List<String> productIdList = orderDTO.getOrderDetailList().stream()
                .map(OrderDetail::getProductId)
                .collect(Collectors.toList());
        List<ProductInfoOutput> productInfoList = productClient.listForOrder(productIdList);
        // 计算总价
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            for (ProductInfoOutput productInfo : productInfoList) {
                if (productInfo.getProductId().equals(orderDetail.getProductId())) {
                    orderAmount = productInfo.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmount);
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    orderDetail.setOrderId(orderId);
                    // 订单详情入库
                    orderDetailDao.save(orderDetail);
                }
            }
        }
        // 扣库存
        List<DecreaseStockInput> decreaseStockInputList = orderDTO.getOrderDetailList().stream()
                .map(e -> new DecreaseStockInput(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStock(decreaseStockInputList);
        // 订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setOrderId(KeyUtil.genUniqueKey());

        orderMasterDao.save(orderMaster);
        return orderDTO;

        // 异步扣库存分析
        // 1.库存在redis保存副本
        // 2.收到请求redis判断库存充足，减掉redis库存
        // 3.订单服务创建订单写入数据库，并发送消息
    }
}
