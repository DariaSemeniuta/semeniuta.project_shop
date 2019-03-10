package com.semeniuta.services.impl;

import com.semeniuta.dao.OrderDao;
import com.semeniuta.dao.ProductDao;
import com.semeniuta.domain.Order;
import com.semeniuta.services.OrderService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Or;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    @Mock
    private OrderDao orderDao;

    private OrderService orderService;
    private Order order;

    @Before
    public void init(){
        orderService = new OrderServiceImpl(orderDao);
        order = new Order(new ArrayList<Long>(Arrays.asList(1l, 2l)), 3l );
    }

    @Test
    public void createOrderSuccessfullyTest() {
        //given
        Mockito.when(orderDao.addOrder(order)).thenReturn(true);
        //when
        boolean result = orderService.createOrder(order.getProducts(), order.getIdClient());
        //then
        Assert.assertTrue(result);
    }

    @Test
    public void createOrderFailedfullyTest() {
        //given
        Mockito.when(orderDao.addOrder(order)).thenReturn(false);
        //when
        boolean result = orderService.createOrder(order.getProducts(), order.getIdClient());
        //then
        Assert.assertFalse(result);
    }

    @Test
    public void editOrderStatusSuccessTest() {
        //given
        long id =8l;
        String status = "Done";
        Mockito.when(orderDao.findOrder(id)).thenReturn(order);
        Mockito.when(orderDao.editOrderStatus(id,status)).thenReturn(true);
        //when
        boolean result = orderService.editOrderStatus(id, status);
        //then
        Assert.assertTrue(result);
    }

    @Test
    public void editOrderStatusFailedTest() {
        //given
        long id =8l;
        String status = "Done";
        Mockito.when(orderDao.findOrder(id)).thenReturn(order);
        Mockito.when(orderDao.editOrderStatus(id,status)).thenReturn(false);
        //when
        boolean result = orderService.editOrderStatus(id, status);
        //then
        Assert.assertFalse(result);
    }

    @Test
    public void deleteOrderSuccessTest() {
        //given
        long id =2l;
        Mockito.when(orderDao.deleteOrder(id)).thenReturn(true);
        //when
        boolean result = orderService.deleteOrder(id);
        //then
        Assert.assertTrue(result);
    }

    @Test
    public void deleteOrderFailedTest() {
        //given
        long id =2l;
        Mockito.when(orderDao.deleteOrder(id)).thenReturn(false);
        //when
        boolean result = orderService.deleteOrder(id);
        //then
        Assert.assertFalse(result);
    }

    @Test
    public void showOrdersExistentTest() {
        //given
        List<Order> orders = new ArrayList<>();
        for(int i=0; i< 3; ++i){
            Order newOrder = new Order(order.getProducts(),order.getIdClient()+i);
            orders.add(newOrder);
        }
        Mockito.when(orderDao.getAllOrders()).thenReturn(orders);
        //when
        List<Order> result = orderService.showOrders();
        //then
        Assert.assertEquals(orders, result);

    }

    @Test
    public void showOrdersNonExistentTest() {
        //given
        Mockito.when(orderDao.getAllOrders()).thenReturn(null);
        //when
        List<Order> result = orderService.showOrders();
        //then
        Assert.assertNull(result);
    }


    @Test
    public void isOrderExistForExistentTest() {
        //given
        long id =9l;
        Mockito.when(orderDao.findOrder(id)).thenReturn(order);
        //when
        boolean result = orderService.isOrderExist(id);
        //then
        Assert.assertTrue(result);
    }

    @Test
    public void isOrderExistForNonExistentTest() {
        //given
        long id =9l;
        Mockito.when(orderDao.findOrder(id)).thenReturn(null);
        //when
        boolean result = orderService.isOrderExist(id);
        //then
        Assert.assertFalse(result);
    }
}