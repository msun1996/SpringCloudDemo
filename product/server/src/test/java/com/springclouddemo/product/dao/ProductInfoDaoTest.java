package com.springclouddemo.product.dao;

import com.springclouddemo.product.ProductApplicationTests;
import com.springclouddemo.product.domain.ProductInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@Component
public class ProductInfoDaoTest extends ProductApplicationTests {

    @Autowired
    ProductInfoDao productInfoDao;

    @Test
    public void findByProductIdIn() {
        List<ProductInfo> productInfoList = productInfoDao.findByProductIdIn(Arrays.asList("157875196366160022","164103465734242707"));
    }

    @Test
    public void findById() {
        ProductInfo productInfo = productInfoDao.findById("157875196366160022");
    }

}