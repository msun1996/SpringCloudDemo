package com.springclouddemo.product.service;

import com.springCloudDemo.product.common.DecreaseStockInput;
import com.springclouddemo.product.ProductApplicationTests;
import com.springclouddemo.product.domain.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ProductServiceTest extends ProductApplicationTests {

    @Autowired
    private ProductService productService;

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfos = productService.findUpAll();
        Assert.assertTrue(productInfos.size()>0);
    }

    @Test
    public void decreaseStock() {
        DecreaseStockInput decreaseStockInput = new DecreaseStockInput("157875196366160022",2);
        productService.decreaseStock(Arrays.asList(decreaseStockInput));
    }
}