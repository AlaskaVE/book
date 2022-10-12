package com.learn.test;

import com.learn.pojo.Cart;
import com.learn.pojo.CartItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    @Test
    void addItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"Java从入门",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"Java从入门",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"数据结构与算法",1,new BigDecimal(100),new BigDecimal(100)));
        System.out.println(cart);
    }

    @Test
    void deleteItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"Java从入门",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"Java从入门",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"数据结构与算法",1,new BigDecimal(100),new BigDecimal(100)));
        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    void clear() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"Java从入门",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"Java从入门",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"数据结构与算法",1,new BigDecimal(100),new BigDecimal(100)));
        cart.deleteItem(1);
        cart.clear();
        System.out.println(cart);
    }

    @Test
    void updateCount() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"Java从入门",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"Java从入门",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"数据结构与算法",1,new BigDecimal(100),new BigDecimal(100)));
        cart.deleteItem(1);
        cart.clear();
        cart.addItem(new CartItem(1,"Java从入门",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.updateCount(1,10);
        System.out.println(cart);
    }
}