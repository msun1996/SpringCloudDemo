package com.springclouddemo.product.service.impl;

import com.springclouddemo.product.dao.ProductCategoryDao;
import com.springclouddemo.product.domain.ProductCategory;
import com.springclouddemo.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author: mSun
 * date: 2018/10/15
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryDao.findByCategoryTypeIn(categoryTypeList);
    }
}
