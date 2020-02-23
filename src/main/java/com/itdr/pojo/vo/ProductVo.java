package com.itdr.pojo.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ProductVo {
    private Integer id;

    private String pname;

    private Long price;

    private String img;

    private Integer status;

    private Integer count;

    private String state;
    @JsonFormat(locale ="zh", timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(locale ="zh", timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String imagHost;
}
