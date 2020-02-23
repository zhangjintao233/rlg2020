package com.itdr.service.impl;

import com.itdr.common.ServerResponse;
import com.itdr.config.ConstCode;
import com.itdr.mapper.CategoryMapper;
import com.itdr.pojo.Category;
import com.itdr.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public ServerResponse<CategoryService> fen(Integer pid) {
        if (pid == null || pid<0)return ServerResponse.defeatedRS(
                ConstCode.DEFAULT_FAIL,
                ConstCode.ProductEnum.UNLAWFULNESS_PARAMETER.getDesc()
        );

        List<Category> li = categoryMapper.selectByPid(pid);


        return ServerResponse.successRS(li);
    }
}
