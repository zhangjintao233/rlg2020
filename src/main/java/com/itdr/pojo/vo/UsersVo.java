package com.itdr.pojo.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UsersVo {

    private Integer id;

    private String username;

    private String email;

    private String uhone;

    private String wenti;

    private String daan;

    private String role;
    @JsonFormat(locale ="zh", timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(locale ="zh", timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
