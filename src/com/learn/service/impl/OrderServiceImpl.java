package com.learn.service.impl;

import com.learn.dao.BookDao;
import com.learn.dao.OrderDao;
import com.learn.dao.OrderItemDao;
import com.learn.dao.impl.BookDaoImpl;
import com.learn.dao.impl.OrderDaoImpl;
import com.learn.dao.impl.OrderItemDaoImpl;
import com.learn.pojo.*;
import com.learn.service.OrderService;

import java.util.Date;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public String createOrder(Cart cart, Integer userId) {
        //订单号====唯一性
        String orderId = System.currentTimeMillis()+""+userId;
        //创建一个订单对象
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        //保存订单
        orderDao.savaOrder(order);
//        int i = 12/0;
        //遍历购物车中每一个商品项转化成为订单项保存到数据库
        for(Map.Entry<Integer, CartItem>entry:cart.getItems().entrySet()){
            //获取每一个购物车中的商品项，然后转化为每一个订单项
            CartItem cartItem = entry.getValue();
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(), orderId);
            //保存订单项到数据库
            orderItemDao.savaOrderItem(orderItem);

            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales()+cartItem.getCount());
            book.setStock(book.getStock()-cartItem.getCount());
            bookDao.updateBook(book);

        }
        //清空购物车
        cart.clear();
        return orderId;
    }
}
