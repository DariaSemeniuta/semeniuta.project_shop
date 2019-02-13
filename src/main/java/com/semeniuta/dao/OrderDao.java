package com.semeniuta.dao;

import com.semeniuta.domain.Order;

import java.util.List;

public interface OrderDao {
    boolean addOrder(Order order);
    List<Order> getAllOrders();
}
