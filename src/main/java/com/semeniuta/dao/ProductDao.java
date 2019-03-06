package com.semeniuta.dao;

import com.semeniuta.domain.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductDao extends DaoDB{

    /**
     * Return list of all products
     *
     * @return list of all products
     */
    List<Product> getAllProducts();

    /**
     * Add new product to DAO
     *
     * @param product - Product object that should to be added
     * @return true if product was added, otherwise - false
     */
    boolean addProduct(Product product);

    /**
     * Change existent product
     *
     * @param id - long - id of product object that should to changed
     * @param name    - String - new name of product
     * @param price   - BigDecimal - new price of product
     * @return true if product was changed, otherwise - false
     */
    boolean editProduct(long id, String name, BigDecimal price);

    /**
     * Delete product from DAO
     *
     * @param id - long - id of product object that should be deleted
     * @return true if product was deleted, otherwise - false
     */
    boolean deleteProduct(long id);

    /**
     * Find product by name
     *
     * @param id - long - id of the product
     * @return Product object
     */
    Product findProduct(long id);
}

