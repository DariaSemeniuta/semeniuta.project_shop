package com.semeniuta.services;

import com.semeniuta.domain.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    /**
     * Add product to shop
     * @param name
     * @param price
     * @return boolean value
     */
    boolean addProduct(String name, BigDecimal price);

    /**
     *
     * @param id
     * @return
     */
    boolean editProduct(long id);

    /**
     *
     * @param id
     * @return
     */
    boolean deleteProduct(long id);

    /**
     *
     * @return
     */
    List<Product> showProducts();
}
