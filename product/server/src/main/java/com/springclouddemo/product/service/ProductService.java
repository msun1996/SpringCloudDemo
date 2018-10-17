package com.springclouddemo.product.service;

import com.springCloudDemo.product.common.DecreaseStockInput;
import com.springclouddemo.product.domain.ProductInfo;

import java.util.List;

/**
 * author: mSun
 * date: 2018/10/15
 */
public interface ProductService {

    /**
     * 查询所有在架商品列表
     */
    List<ProductInfo> findUpAll();

    /**
     * 查询商品列表
     * @param productIdList
     * @return
     */
    List<ProductInfo> findList(List<String> productIdList);

    /**
     * 扣库存
     */
    void decreaseStock(List<DecreaseStockInput> decreaseStockInputList);
}
