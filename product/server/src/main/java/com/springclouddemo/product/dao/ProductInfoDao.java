package com.springclouddemo.product.dao;

import com.springclouddemo.product.domain.ProductInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 产品信息
 * author: mSun
 * date: 2018/10/13
 */
@Mapper
@Repository
public interface ProductInfoDao {

    // 查询在架商品列表
    List<ProductInfo> findByProductStatus(Integer productStatus);

    // id查询商品列表
    List<ProductInfo> findByProductIdIn(@Param("productIdList") List<String> productIdList);

    // 根据id查询商品
    ProductInfo findById(String productId);

    // 更新商品
    void updateProduct(ProductInfo productInfo);

}
