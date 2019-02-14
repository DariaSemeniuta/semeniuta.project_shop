package com.semeniuta.dao;

import com.semeniuta.domain.Order;
import com.semeniuta.domain.Product;

import java.util.List;

public interface OrderDao {
    boolean addOrder(Order order);
    List<Order> getAllOrders();
    boolean editOrderStatus(Order order, String status);
    boolean addProductToOrder(Order order, Product product);
    boolean removeProductFromOrder(Order order, Product product);

    boolean deleteOrder(Order order);
    Order findOrder(long id);
}
