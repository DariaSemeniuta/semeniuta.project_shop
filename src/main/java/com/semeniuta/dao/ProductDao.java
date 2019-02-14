package com.semeniuta.dao;

import com.semeniuta.domain.Product;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 */
public interface ProductDao {
    List<Product> returnAllProducts();
    boolean addProduct(Product product);
    boolean editProduct(Product product, String name, BigDecimal price);
    boolean deleteProduct(Product product);
    Product findProduct(String name);
}

