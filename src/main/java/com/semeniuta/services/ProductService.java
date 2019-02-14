package com.semeniuta.services;

import com.semeniuta.domain.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    boolean addProduct(String name, BigDecimal price);


    boolean editProduct(String oldName, String name, BigDecimal price);

    boolean deleteProduct(String name);

    List<Product> showProducts();
}
