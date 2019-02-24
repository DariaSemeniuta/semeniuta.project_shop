package com.semeniuta.dao.impl;

import com.semeniuta.dao.ProductDao;
import com.semeniuta.domain.Client;
import com.semeniuta.domain.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDaoImpl implements ProductDao {
    private Map<Long, Product> products;
    private static long generator = 0;

    public ProductDaoImpl() {
        products = new HashMap<>();
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    @Override
    public boolean addProduct(Product product) {
        product.setId(generator++);
        products.put(product.getId(), product);
        return true;
    }

    @Override
    public boolean editProduct(long id, String name, BigDecimal price) {
        Product product = products.get(id);
        product.setName(name);
        product.setPrice(price);
        return true;
    }

    @Override
    public boolean deleteProduct(long id) {
        products.remove(id);
        return true;
    }

    @Override
    public Product findProduct(long id) {
        if (products.containsKey(id)) {
                return products.get(id);
            }
        return null;
    }
}
