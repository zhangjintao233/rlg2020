package com.itdr.utils;

import com.itdr.pojo.Users;
import com.itdr.pojo.vo.UsersVo;

public class ObjectUtils {



    public static UsersVo Users(Users u){
        UsersVo uv = new UsersVo();
        uv.setId(u.getId());
        uv.setUsername(u.getUsername());
        uv.setEmail(u.getEmail());
        uv.setUhone(u.getUhone());
        uv.setCreateTime(u.getCreateTime());
        uv.setUpdateTime(u.getUpdateTime());
        return uv;
    }
}
