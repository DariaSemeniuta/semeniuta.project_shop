package com.semeniuta.services.impl;

import com.semeniuta.dao.OrderDao;
import com.semeniuta.dao.ProductDao;
import com.semeniuta.dao.impl.OrderDaoImpl;
import com.semeniuta.dao.impl.ProductDaoImpl;
import com.semeniuta.domain.Order;
import com.semeniuta.domain.Product;
import com.semeniuta.services.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao = new OrderDaoImpl();
    private final ProductDao productDao = new ProductDaoImpl();

    @Override
    public boolean editOrder() {
        return false;
    }

    @Override
    public boolean createOrder(String productName) {
        Product product = productDao.findProduct(productName);
        Order order = new Order(product);
        return orderDao.addOrder(order);
    }

    @Override
    public boolean editOrderStatus(long id, String status) {
        Order order = orderDao.findOrder(id);
        return orderDao.editOrderStatus(order, status);
    }

    @Override
    public boolean addProductToOrder(long orderId, String productName) {
        Order order = orderDao.findOrder(orderId);
        Product product = productDao.findProduct(productName);
        return orderDao.addProductToOrder(order, product);
    }

    @Override
    public boolean removeProductFromOrder(long orderId, String productName) {
        Order order = orderDao.findOrder(orderId);
        Product product = productDao.findProduct(productName);
        return orderDao.removeProductFromOrder(order, product);
    }

    @Override
    public boolean deleteOrder(long orderId) {
        Order order = orderDao.findOrder(orderId);
        return orderDao.deleteOrder(order);
    }

    @Override
    public List<Order> showOrders() {
        return orderDao.getAllOrders();
    }
}
