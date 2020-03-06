package com.itdr.service;

import com.itdr.common.ServerResponse;
import com.itdr.pojo.Users;

public interface OrderService {
    ServerResponse create(Integer shippingId, Users user);

    ServerResponse getAllOrder(Users user,Integer pageNum,Integer pageSize,String orderBy);

    ServerResponse getOrderItem(Users user);
}
