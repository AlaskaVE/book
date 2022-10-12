package com.learn.service;

import com.learn.pojo.Cart;

public interface OrderService {
    public String createOrder(Cart cart,Integer userId);
}
