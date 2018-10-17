package com.springclouddemo.product.service;

import com.springclouddemo.product.ProductApplicationTests;
import com.springclouddemo.product.dao.ProductCategoryDao;
import com.springclouddemo.product.domain.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CategoryServiceTest extends ProductApplicationTests {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> productCategories = productCategoryDao.findByCategoryTypeIn(Arrays.asList(11,22));
        Assert.assertTrue(productCategories.size()>0);
    }
}