package com.semeniuta.dao;

import com.semeniuta.domain.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductDao {

    /**
     * Return list of all products
     * @return list of all products
     */
    List<Product> returnAllProducts();

    /**
     * Add new product to DAO
     * @param product - Product object that should to be added
     * @return true if product was added, otherwise - false
     */
    boolean addProduct(Product product);

    /**
     * Change existent product
     * @param product - Product - product object that should to changed
     * @param name - String - new name of product
     * @param price - BigDecimal - new price of product
     * @return true if product was changed, otherwise - false
     */
    boolean editProduct(Product product, String name, BigDecimal price);

    /**
     * Delete product from DAO
     * @param product - Product - product object that should be deleted
     * @return true if product was deleted, otherwise - false
     */
    boolean deleteProduct(Product product);

    /**
     * Find product by name
     * @param id - long - id of the product
     * @return Product object
     */
    Product findProduct(long id);
}

