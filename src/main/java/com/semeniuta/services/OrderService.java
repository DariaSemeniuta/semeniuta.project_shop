package com.semeniuta.services;

import com.semeniuta.domain.Order;

import java.util.List;

public interface OrderService {
    /**
     * Create new order.
     * !!! Currently accept only one product, it will be changed in next orderService branch
     * @param productName - String - name of product that should be ordered
     * @return true if order was created, otherwise - false
     */
    boolean createOrder(String productName);

    /**
     * Change status of existent order
     * !!! enum with statuses of order will be added in next orderService branch
     * @param id - long - id of the order
     * @param status - new status f order that should be changed
     * @return true if status of order was changed, otherwise - false
     */
    boolean editOrderStatus(long id, String status);

    /**
     * Add product to existent order
     * !!! not implemented yet
     * @param orderId - long - id of the order
     * @param productName - String - name of the product that should be added to order
     * @return true if product was successfully added to order, otherwise - false
     */
    boolean addProductToOrder(long orderId, String productName);

    /**
     * Remove product from existent order
     * @param orderId - long - id of the order
     * @param productName- String - name of the product that should be removed from order
     * @return true if product was successfully removed from order, otherwise - false
     */
    boolean removeProductFromOrder(long orderId, String productName);

    /**
     * Delete order
     * @param orderId - long - id of order that should be deleted
     * @return true if order was successfully removed, otherwise - false
     */
    boolean deleteOrder(long orderId);

    /**
     * Get list of all orders
     * @return - list of all orders
     */
    List<Order> showOrders();

}
