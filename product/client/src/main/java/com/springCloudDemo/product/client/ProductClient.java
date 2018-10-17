package com.springCloudDemo.product.client;

import com.springCloudDemo.product.common.DecreaseStockInput;
import com.springCloudDemo.product.common.ProductInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * author: mSun
 * date: 2018/10/17
 */
@FeignClient(name = "product")
public interface ProductClient {
    /**
     * id列表获取商品列表
     */
    @PostMapping("/product/listForOrder")
    List<ProductInfoOutput> listForOrder(@RequestBody List<String> productIdList);

    /**
     * 扣库存
     */
    @PostMapping("/product/decreaseStock")
    void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList);

}
