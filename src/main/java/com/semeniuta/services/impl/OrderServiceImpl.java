package com.semeniuta.services.impl;

import com.semeniuta.dao.OrderDao;
import com.semeniuta.dao.ProductDao;
import com.semeniuta.domain.Order;
import com.semeniuta.domain.Product;
import com.semeniuta.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private final OrderDao orderDao;
    private final ProductDao productDao;
    @Autowired
    public OrderServiceImpl(OrderDao orderDao, ProductDao productDao) {
        this.orderDao = orderDao;
        this.productDao = productDao;
    }

    @Override
    public boolean createOrder(List<Long> products, long idClient) {
        List<Product> productList = new ArrayList<>();
        for (Long product_id: products) {
            productList.add(productDao.findProduct(product_id));
        }
        Order order = new Order(productList, idClient);
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
        if (orderDao.findOrder(id) == null) {
            return false;
        }
        return true;
    }
}
