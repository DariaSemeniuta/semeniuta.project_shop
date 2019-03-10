package com.semeniuta.services.impl;

import com.semeniuta.dao.OrderDao;
import com.semeniuta.dao.ProductDao;
import com.semeniuta.domain.Order;
import com.semeniuta.services.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
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

    @Override
    public boolean isOrderExist(long id) {
        if(orderDao.findOrder(id)==null){
            return false;
        }
        return true;
    }
}
