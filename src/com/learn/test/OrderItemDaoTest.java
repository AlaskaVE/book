package com.learn.test;

import com.learn.dao.OrderItemDao;
import com.learn.dao.impl.OrderItemDaoImpl;
import com.learn.pojo.OrderItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemDaoTest {

    @Test
    void savaOrderItem() {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();
        orderItemDao.savaOrderItem(new OrderItem(null,"八嘎八嘎八嘎",1,new BigDecimal(100),new BigDecimal(100),"1234567890"));
        orderItemDao.savaOrderItem(new OrderItem(null,"哈哈哈哈",2,new BigDecimal(100),new BigDecimal(200),"1234567890"));
        orderItemDao.savaOrderItem(new OrderItem(null,"嗯嗯嗯嗯嗯嗯",1,new BigDecimal(100),new BigDecimal(100),"1234567890"));
    }
}