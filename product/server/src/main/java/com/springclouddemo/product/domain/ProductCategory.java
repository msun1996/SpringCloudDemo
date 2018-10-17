package com.springclouddemo.product.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * author: mSun
 * date: 2018/10/13
 */
@Data
@ApiModel(value = "产品类目")
public class ProductCategory {

    @ApiModelProperty(value = "类目ID")
    private Integer categoryId;

    @ApiModelProperty(value = "类目名")
    private String categoryName;

    @ApiModelProperty(value = "类目编号")
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;
}
