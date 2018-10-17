package com.springclouddemo.product.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * author: mSun
 * date: 2018/10/13
 */
@Data
@ApiModel(value = "产品信息")
public class ProductInfo {

    @ApiModelProperty(value = "编号")
    private String productId;

    @ApiModelProperty(value = "名字")
    private String productName;

    @ApiModelProperty(value = "单价")
    private BigDecimal productPrice;

    @ApiModelProperty(value = "库存")
    private Integer productStock;

    @ApiModelProperty(value = "描述")
    private String productDescription;

    @ApiModelProperty(value = "小图")
    private String productIcon;

    @ApiModelProperty(value = "状态, 0正常1下架")
    private Integer productStatus;

    @ApiModelProperty(value = "类目编号")
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;
}
