package com.semeniuta.services;

import com.semeniuta.domain.Order;

import java.util.List;

public interface OrderService {
    /**
     * Create new order.
     * !!! Currently accept only one product, it will be changed in next orderService branch
     *
     * @param products - List<long></> - ids of products that should be ordered
     * @return true if order was created, otherwise - false
     */
    boolean createOrder(List<Long> products, long idClient);

    /**
     * Change status of existent order
     * !!! enum with statuses of order will be added in next orderService branch
     *
     * @param id     - long - id of the order
     * @param status - new status f order that should be changed
     * @return true if status of order was changed, otherwise - false
     */
    boolean editOrderStatus(long id, String status);

    /**
     * Delete order
     *
     * @param orderId - long - id of order that should be deleted
     * @return true if order was successfully removed, otherwise - false
     */
    boolean deleteOrder(long orderId);

    /**
     * Get list of all orders
     *
     * @return - list of all orders
     */
    List<Order> showOrders();

    boolean isOrderExist(long id);

}
