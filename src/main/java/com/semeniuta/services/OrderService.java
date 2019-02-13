package com.semeniuta.services;

import com.semeniuta.domain.Order;

import java.util.List;

public interface OrderService {
    boolean editOrder();
    boolean createOrder(long productId);

    boolean deleteOrder();
    List<Order> showOrders();
    Order findOrder();
}
