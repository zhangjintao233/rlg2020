package com.itdr.service;

import com.itdr.common.ServerResponse;
import com.itdr.pojo.Users;

public interface OrderService {
    ServerResponse create(Integer shippingId, Users user);
}
