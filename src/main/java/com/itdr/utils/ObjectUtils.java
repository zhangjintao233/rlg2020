package com.itdr.utils;

import com.itdr.pojo.Product;
import com.itdr.pojo.Users;
import com.itdr.pojo.vo.ProductVo;
import com.itdr.pojo.vo.UsersVo;

public class ObjectUtils {


    /**
     * 封装用户
     * @param u
     * @return
     */
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

    /**
     * 封装商品
     * @param p
     * @return
     */
    public static ProductVo product(Product p){
        ProductVo pv = new ProductVo();
        pv.setId(p.getId());
        pv.setPname(p.getPname());
        pv.setPrice(p.getPrice());
        pv.setImg(p.getImg());
        pv.setStatus(p.getStatus());
        pv.setCount(p.getCount());
        pv.setState(p.getState());
        pv.setCreateTime(p.getCreateTime());
        pv.setUpdateTime(p.getUpdateTime());
        pv.setImagHost(ProductTiesUtil.getValue("imageHost"));
        return pv;
    }
}
