package com.itdr.service;

import com.itdr.common.ServerResponse;
import com.itdr.pojo.Users;

public interface GwcService {
    ServerResponse list(Users user);

    ServerResponse add(Integer productId, Integer count, Integer type, Users user);

    ServerResponse update(Integer productId, Integer count, Integer type, Users user);

    ServerResponse delete(Integer productId, Users user);

    ServerResponse selectUserProduct(Users user);

    ServerResponse checked(Integer productId, Integer type, Users user);

    ServerResponse over(Users user);
}
