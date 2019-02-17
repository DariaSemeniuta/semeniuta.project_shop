package com.semeniuta.dao.impl;

import com.semeniuta.dao.OrderDao;
import com.semeniuta.domain.Order;
import com.semeniuta.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    private List<Order> orders;
    private static long generator = 0;

    public OrderDaoImpl() {
        this.orders = new ArrayList<>();
    }

    @Override
    public boolean addOrder(Order order) {
        order.setId(generator++);
        orders.add(order);
        return true;
    }

    @Override
    public List<Order> getAllOrders() {
        return orders;
    }

    @Override
    public boolean editOrderStatus(Order order, String status) {
        order.setStatus(status);
        return true;
    }

    @Override
    public boolean deleteOrder(Order order) {
        orders.remove(order);
        return true;
    }

    @Override
    public Order findOrder(long id) {
        for (Order order: orders) {
            if(order.getId() == id){
                return order;
            }
        }
        return null;
    }
}
