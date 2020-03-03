package com.itdr.pojo.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class OrderItemVO {

    private Integer pid;

    private String pname;

    private String pimg;

    private Long currentUnitPrice;

    private Integer quantity;

    private BigDecimal totalPrice;

}
