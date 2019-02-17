package com.semeniuta.dao.impl;

import com.semeniuta.dao.ProductDao;
import com.semeniuta.domain.Client;
import com.semeniuta.domain.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
private List<Product> products ;
private static long generator = 0;
    public ProductDaoImpl() {

        products = new ArrayList<>();
    }
    @Override
    public List<Product> returnAllProducts() {

        return products;
    }

    @Override
    public boolean addProduct(Product product) {
        product.setId(generator++);
        products.add(product);
        return true;
    }

    @Override
    public boolean editProduct(Product product, String name, BigDecimal price) {
        product.setName(name);
        product.setPrice(price);
        return true;
    }

    @Override
    public boolean deleteProduct(Product product) {
        products.remove(product);
        return true;
    }

    @Override
    public Product findProduct(long id) {
        for (Product product: products) {
            if(product.getId() == id){
                return product;
            }
        }
        return null;
    }
}
