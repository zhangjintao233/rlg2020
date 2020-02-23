package com.itdr.service;

import com.itdr.common.ServerResponse;
import com.itdr.pojo.Product;
import com.itdr.pojo.vo.ProductVo;

public interface ProductService {
    ServerResponse<ProductVo> selectProductById(Integer productId);

    ServerResponse<ProductVo> selectProductByName(String word,Integer pageNum,Integer pageSize,String orderBy);
}
