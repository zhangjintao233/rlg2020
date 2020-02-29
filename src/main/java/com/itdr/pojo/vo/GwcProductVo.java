package com.itdr.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class GwcProductVo {
    private Integer id;

    private Integer uid;

    private Integer pid;

    private Integer quantiy;

    private Integer checked;


    private String productName;

    private Long productPrice;

    private String productImg;

    private Integer status;

    private Integer productCount;

    private Integer productState;

    private BigDecimal productTotalPrice;

    private String xinyang;
}
