package com.semeniuta.dao.impl;

import com.semeniuta.dao.OrderDao;
import com.semeniuta.domain.Order;
import com.semeniuta.domain.Product;
import com.sun.tools.corba.se.idl.constExpr.Or;

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

    @Override
    public boolean editOrderStatus(Order order, String status) {
        order.setStatus(status);
        return true;
    }

    @Override
    public boolean addProductToOrder(Order order, Product product) {
        order.addProduct(product);
        return true;
    }

    @Override
    public boolean removeProductFromOrder(Order order, Product product) {
        order.deleteProduct(product);
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
