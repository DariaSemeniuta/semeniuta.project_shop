package com.semeniuta.dao.impl;

import com.semeniuta.dao.ProductDao;
import com.semeniuta.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

@Service
@Primary
public class ProductEMDao implements ProductDao {

    private EntityManager entityManager;

    @Autowired
    public ProductEMDao(EntityManagerFactory factory) {
       this.entityManager = factory.createEntityManager();
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = null;
        products = entityManager.createQuery("from Product", Product.class).getResultList();
        return products;
    }

    @Override
    public boolean addProduct(Product product) {
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(product);
            entityManager.getTransaction().commit();
            return true;
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean editProduct(long id, String name, BigDecimal price) {
        try {
            entityManager.getTransaction().begin();
            Product product = entityManager.find(Product.class, id);
            product.setName(name);
            product.setPrice(price);
            entityManager.getTransaction().commit();
            return true;
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteProduct(long id) {
        try {
            entityManager.getTransaction().begin();
            Product product = entityManager.find(Product.class,id);
            entityManager.remove(product);
            entityManager.getTransaction().commit();
            return true;
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Product findProduct(long id) {
        Product product = null;
        product = entityManager.find(Product.class,id);
        return product;
    }
}
