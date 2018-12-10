package com.springclouddemo.product.service.impl;

import com.springCloudDemo.product.common.DecreaseStockInput;
import com.springCloudDemo.product.common.ProductInfoOutput;
import com.springclouddemo.product.dao.ProductInfoDao;
import com.springclouddemo.product.domain.ProductInfo;
import com.springclouddemo.product.enums.ProductStatusEnum;
import com.springclouddemo.product.enums.ResultEnum;
import com.springclouddemo.product.exception.ProductException;
import com.springclouddemo.product.service.ProductService;
import com.springclouddemo.product.utils.JsonUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * author: mSun
 * date: 2018/10/15
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoDao.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfo> findList(List<String> productIdList) {
        return productInfoDao.findByProductIdIn(productIdList);
    }

    @Override

    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {

        List<ProductInfo> productInfoList = decreaseStockProcess(decreaseStockInputList);

        // 发送mq消息
        List<ProductInfoOutput> productInfoOutputList = productInfoList.stream().map(e->{
            ProductInfoOutput productInfoOutput = new ProductInfoOutput();
            BeanUtils.copyProperties(e, productInfoOutput);
            return productInfoOutput;
        }).collect(Collectors.toList());
        amqpTemplate.convertAndSend("productInfo", JsonUtil.toJson(productInfoOutputList));
    }

    @Transactional
    public List<ProductInfo> decreaseStockProcess(List<DecreaseStockInput> decreaseStockInputList) {
        List<ProductInfo> productInfoList = new ArrayList<>();
        for (DecreaseStockInput decreaseStockInput: decreaseStockInputList) {
            // 查询商品已有库存
            ProductInfo productInfo = productInfoDao.findById(decreaseStockInput.getProductId());
            // 判断商品是否存在
            if (productInfo == null) {
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            // 判断库存是否足够
            Integer quantity = productInfo.getProductStock() - decreaseStockInput.getProductQuantity();
            if (quantity < 0){
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(quantity);
            productInfoDao.updateProduct(productInfo);

            productInfoList.add(productInfo);
        }
        return productInfoList;
    }
}
