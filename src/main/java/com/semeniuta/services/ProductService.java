package com.semeniuta.services;

import com.semeniuta.domain.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    /**
     * Add new product
     * @param name - String - name of the new product
     * @param price - BigDecimal - price of the new product
     * @return true if product was added, otherwise - false
     */
    boolean addProduct(String name, BigDecimal price);

    /**
     * Allow to change name or price of existent  product
     * @param oldName - String - name of the existent product
     * @param name - String - new name of the changed product
     * @param price - BigDecimal - new price of the changed product
     * @return true if product was changed, otherwise - false
     */
    boolean editProduct(String oldName, String name, BigDecimal price);

    /**
     * Delete product
     * @param name - String - name of the existent product that should be deleted
     * @return true if product was deleted, otherwise - false
     */
    boolean deleteProduct(String name);

    /**
     * Get list of all products
     * @return list of all existent products
     */
    List<Product> showProducts();
}
