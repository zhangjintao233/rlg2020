package com.itdr.service;

import com.itdr.common.ServerResponse;

public interface CategoryService {
    ServerResponse<CategoryService> fen(Integer pid);
}
