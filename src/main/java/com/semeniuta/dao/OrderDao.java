package com.semeniuta.dao;

import com.semeniuta.domain.Order;
import com.semeniuta.domain.Product;

import java.util.List;

public interface OrderDao {

    /**
     * Add new order to DAO
     *
     * @param order - Order - object that should be added
     * @return true if order was successfully added, otherwise - false
     */
    boolean addOrder(Order order);

    /**
     * Return all orders
     *
     * @return list of all orders
     */
    List<Order> getAllOrders();

    /**
     * Change status of order
     *
     * @param order  - Order - order object that should be changed
     * @param status - String - new status of order
     * @return true if status was successfully changed, otherwise - false
     */
    boolean editOrderStatus(Order order, String status);


    /**
     * Delete order from DAO
     *
     * @param order - Order - order object that should be deleted
     * @return true if order was successfully removed, otherwise - false
     */
    boolean deleteOrder(Order order);

    /**
     * Find order by id // other criterias for search will be implemented later
     *
     * @param id - long - id of order
     * @return Order object
     */
    Order findOrder(long id);
}
