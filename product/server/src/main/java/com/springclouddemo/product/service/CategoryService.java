package com.springclouddemo.product.service;

import com.springclouddemo.product.domain.ProductCategory;

import java.util.List;

/**
 * 类目
 * author: mSun
 * date: 2018/10/15
 */
public interface CategoryService {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
