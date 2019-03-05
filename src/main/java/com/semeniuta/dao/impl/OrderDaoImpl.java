package com.semeniuta.dao.impl;

import com.semeniuta.dao.OrderDao;
import com.semeniuta.domain.Order;
import com.semeniuta.domain.Product;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl implements OrderDao {

    private Map<Long, Order> orders;
    private static long generator = 0;

    public OrderDaoImpl() {
        orders = new HashMap<>();
    }

    @Override
    public boolean addOrder(Order order) {
        order.setId(generator++);
        orders.put(order.getId(), order);
        return true;
    }

    @Override
    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }


    @Override
    public boolean editOrderStatus(long id, String status) {
        Order order = orders.get(id);
        order.setStatus(status);
        return true;
    }

    @Override
    public boolean deleteOrder(long id) {
        orders.remove(id);
        return true;
    }

    @Override
    public Order findOrder(long id) {
        if (orders.containsKey(id)) {
                return orders.get(id);
        }
        return null;
    }
}
