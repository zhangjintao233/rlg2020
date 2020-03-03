package com.itdr.pojo.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class OrderVo {

    private Long orderNo;

    private Integer shippingId;

    private BigDecimal payment;

    private Integer paymentType;

    private Integer postage;

    private Integer status;

    private List<OrderItemVO> orderItemVOList;

    private ShippingVo shippingVo;

    private String imageHost;
}
