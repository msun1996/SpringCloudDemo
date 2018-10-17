package com.springclouddemo.order.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * author: mSun
 * date: 2018/10/15
 */
@Data
@ApiModel(value = "订单详情")
public class OrderDetail {

    @ApiModelProperty("订单详情id")
    private String detailId;

    @ApiModelProperty("订单id")
    private String orderId;

    @ApiModelProperty("商品id")
    private String productId;

    @ApiModelProperty("商品名称")
    private String productName;

    @ApiModelProperty("当前价格,单位分")
    private BigDecimal productPrice;

    @ApiModelProperty("数量")
    private Integer productQuantity;

    @ApiModelProperty("小图")
    private String productIcon;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date updateTime;
}
