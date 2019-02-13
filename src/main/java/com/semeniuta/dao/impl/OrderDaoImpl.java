package com.semeniuta.dao.impl;

import com.semeniuta.dao.OrderDao;
import com.semeniuta.domain.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    private List<Order> orders = new ArrayList<>();
    @Override
    public boolean addOrder(Order order) {
        orders.add(order);
        return true;
    }

    @Override
    public List<Order> getAllOrders() {
        return orders;
    }
}
