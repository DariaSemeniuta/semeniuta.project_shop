package com.semeniuta.dao.impl;

import com.semeniuta.dao.OrderDao;
import com.semeniuta.domain.Order;
import org.hibernate.HibernateException;
import org.mockito.internal.matchers.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Service
@Primary
public class OrderEMDao implements OrderDao{
    private EntityManager entityManager;

    @Autowired
    public OrderEMDao(EntityManagerFactory factory) {
        this.entityManager = factory.createEntityManager();
    }

    @Override
    public boolean addOrder(Order order) {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(order);
            entityManager.getTransaction().commit();
            return true;
        }catch (HibernateException e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = null;
        orders = entityManager.createQuery("from Order", Order.class).getResultList();
        return orders;
    }

    @Override
    public boolean editOrderStatus(long id, String status) {
        try {
            entityManager.getTransaction().begin();
            Order order = entityManager.find(Order.class,id);
            order.setStatus(status);
            entityManager.getTransaction().commit();
            return true;
        }catch (HibernateException e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteOrder(long id) {
        try {
            entityManager.getTransaction().begin();
            Order order = entityManager.find(Order.class,id);
            entityManager.remove(order);
            entityManager.getTransaction().commit();
            return true;
        }catch (HibernateException e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Order findOrder(long id) {
        Order order = null;
        order = entityManager.find(Order.class,id);
        return order;
    }
}

