package com.semeniuta.services.impl;

import com.semeniuta.dao.OrderDao;
import com.semeniuta.dao.impl.OrderDaoImpl;
import com.semeniuta.domain.Order;
import com.semeniuta.services.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao = new OrderDaoImpl();
    @Override
    public boolean editOrder() {
        return false;
    }

    @Override
    public boolean createOrder(long productId) {
        return orderDao.addOrder(new Order(productId));
    }

    @Override
    public boolean deleteOrder() {
        return false;
    }

    @Override
    public List<Order> showOrders() {
        return orderDao.getAllOrders();
    }

    @Override
    public Order findOrder() {
        return null;
    }
}
