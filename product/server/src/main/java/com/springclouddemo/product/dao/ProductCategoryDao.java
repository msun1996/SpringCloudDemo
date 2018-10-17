package com.springclouddemo.product.dao;

import com.springclouddemo.product.domain.ProductCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 产品类目
 * author: mSun
 * date: 2018/10/13
 */
@Mapper
@Repository
public interface ProductCategoryDao {
    List<ProductCategory> findByCategoryTypeIn(@Param("categoryTypeList") List<Integer> categoryTypeList);
}
