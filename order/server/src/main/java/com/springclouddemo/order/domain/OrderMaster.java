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
@ApiModel(value = "订单")
public class OrderMaster {

    @ApiModelProperty("订单号")
    private String orderId;

    @ApiModelProperty("买家名")
    private String buyerName;

    @ApiModelProperty("买家电话")
    private String buyerPhone;

    @ApiModelProperty("买家地址")
    private String buyerAddress;

    @ApiModelProperty("买家微信openId")
    private String buyerOpenid;

    @ApiModelProperty("订单总额")
    private BigDecimal orderAmount;

    @ApiModelProperty("订单状态,0为新下单")
    private Integer orderStatus;

    @ApiModelProperty("支付状态,0为未支付")
    private Integer payStatus;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改时间")
    private Date updateTime;

}
