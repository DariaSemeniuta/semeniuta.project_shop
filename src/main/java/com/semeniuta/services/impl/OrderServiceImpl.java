package com.semeniuta.services.impl;

import com.semeniuta.dao.OrderDao;
import com.semeniuta.dao.ProductDao;
import com.semeniuta.dao.impl.OrderDaoImpl;
import com.semeniuta.dao.impl.ProductDaoImpl;
import com.semeniuta.domain.Order;
import com.semeniuta.domain.Product;
import com.semeniuta.services.OrderService;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    private final ProductDao productDao;

    public OrderServiceImpl(OrderDao orderDao, ProductDao productDao) {
        this.orderDao = orderDao;
        this.productDao = productDao;
    }

    @Override
    public boolean createOrder(List<Long> products, long idClient) {
        Order order = new Order(products, idClient);
        return orderDao.addOrder(order);
    }

    @Override
    public boolean editOrderStatus(long id, String status) {
        Order order = orderDao.findOrder(id);
        return orderDao.editOrderStatus(id, status);
    }


    @Override
    public boolean deleteOrder(long orderId) {
       return orderDao.deleteOrder(orderId);
    }

    @Override
    public List<Order> showOrders() {
        return orderDao.getAllOrders();
    }
}
