package com.springCloudDemo.product.common;

import lombok.Data;

/**
 * 减库存入参
 * author: mSun
 * date: 2018/10/17
 */
@Data
public class DecreaseStockInput {
    // 商品id
    private String productId;
    // 商品数量
    private Integer productQuantity;

    public DecreaseStockInput() {
    }

    public DecreaseStockInput(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
