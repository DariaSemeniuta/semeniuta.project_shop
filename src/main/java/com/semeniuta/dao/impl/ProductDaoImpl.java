package com.semeniuta.dao.impl;

import com.semeniuta.dao.ProductDao;
import com.semeniuta.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
private List<Product> products;

    public ProductDaoImpl() {
        products = new ArrayList<>();
    }
    @Override
    public List<Product> returnAllProducts() {
        return products;
    }
}
