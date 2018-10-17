package com.springclouddemo.product.service.impl;

import com.springCloudDemo.product.common.DecreaseStockInput;
import com.springclouddemo.product.dao.ProductInfoDao;
import com.springclouddemo.product.domain.ProductInfo;
import com.springclouddemo.product.enums.ProductStatusEnum;
import com.springclouddemo.product.enums.ResultEnum;
import com.springclouddemo.product.exception.ProductException;
import com.springclouddemo.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * author: mSun
 * date: 2018/10/15
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoDao productInfoDao;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoDao.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfo> findList(List<String> productIdList) {
        return productInfoDao.findByProductIdIn(productIdList);
    }

    @Override
    @Transactional
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {
        for (DecreaseStockInput decreaseStockInput: decreaseStockInputList) {
            // 查询商品已有库存
            ProductInfo productInfo = productInfoDao.findById(decreaseStockInput.getProductId());
            // 判断商品是否存在
            if (productInfo == null) {
                throw new ProductException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            // 判断库存是否足够
            Integer quantity = productInfo.getProductStock() - decreaseStockInput.getProductQuantity();
            if (quantity < 0){
                throw new ProductException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(quantity);
            productInfoDao.updateProduct(productInfo);
        }
    }

}
