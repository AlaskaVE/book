package com.learn.dao.impl;

import com.learn.dao.OrderDao;
import com.learn.pojo.Order;

public class OrderDaoImpl extends BaseDao implements OrderDao {

    @Override
    public int savaOrder(Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";
      return   update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());

    }

}
