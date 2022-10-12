package com.learn.test;

import com.learn.pojo.Cart;
import com.learn.pojo.CartItem;
import com.learn.service.OrderService;
import com.learn.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    @Test
    void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"Java从入门",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"Java从入门",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"数据结构与算法",1,new BigDecimal(100),new BigDecimal(100)));

        OrderService orderService = new OrderServiceImpl();
        System.out.println("订单号是："+orderService.createOrder(cart,1));
    }
}