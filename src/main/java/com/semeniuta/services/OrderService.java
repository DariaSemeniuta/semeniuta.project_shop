package com.semeniuta.services;

import com.semeniuta.domain.Order;

import java.util.List;

public interface OrderService {
    boolean editOrder();
    boolean createOrder(String productName);
    boolean editOrderStatus(long id, String status);
    boolean addProductToOrder(long orderId, String productName);
    boolean removeProductFromOrder(long orderId, String productName);
    boolean deleteOrder(long orderId);
    List<Order> showOrders();

}
