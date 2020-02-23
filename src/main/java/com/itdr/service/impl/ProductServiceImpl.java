package com.itdr.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itdr.common.ServerResponse;
import com.itdr.config.ConstCode;
import com.itdr.mapper.ProductMapper;
import com.itdr.pojo.Product;
import com.itdr.pojo.vo.ProductVo;
import com.itdr.service.ProductService;
import com.itdr.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductMapper productMapper;


    @Override
    public ServerResponse<ProductVo> selectProductById(Integer productId) {
        if (productId == null || productId < 0)return ServerResponse.defeatedRS(
                ConstCode.DEFAULT_FAIL,
                ConstCode.ProductEnum.UNLAWFULNESS_PARAMETER.getDesc()
        );

        Product product = productMapper.selectById(productId);

        if (product == null || product.getStatus() < 1)return ServerResponse.defeatedRS(
                ConstCode.DEFAULT_FAIL,
                ConstCode.ProductEnum.NULL_PRODUCT.getDesc()
        );
        ProductVo productVo = ObjectUtils.product(product);
        return ServerResponse.successRS(productVo);
    }

    @Override
    public ServerResponse<ProductVo> selectProductByName(String word,Integer pageNum,Integer pageSize,String orderBy) {
        if (StringUtils.isEmpty(word))return ServerResponse.defeatedRS(
                ConstCode.DEFAULT_FAIL,
                ConstCode.ProductEnum.NULL_PARAMETER.getDesc()
        );

        String[] split = new String[2];
        if (!StringUtils.isEmpty(orderBy)){
            split = orderBy.split("_");
        }

        String keyWord = "%"+word+"%";
        // 开启分页
        PageHelper.startPage(pageNum,pageSize,split[0]+" "+split[1]);
        List<Product> li = productMapper.selectByName(keyWord);

        PageInfo pageInfo = new PageInfo(li);
        List<ProductVo> liNew = new ArrayList<ProductVo>();
        for (Product p : li){
            ProductVo product = ObjectUtils.product(p);
            liNew.add(product);
        }
        pageInfo.setList(liNew);
        return ServerResponse.successRS(pageInfo);
    }
}
