package com.learn.test;

import com.learn.dao.OrderDao;
import com.learn.dao.impl.OrderDaoImpl;
import com.learn.pojo.Order;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class OrderDaoTest {

    @Test
    void savaOrder() {
        OrderDao orderDao = new OrderDaoImpl();
        orderDao.savaOrder(new Order("1234567890",new Date(),new BigDecimal(100),0,1));

    }
}