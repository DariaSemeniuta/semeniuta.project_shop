package com.semeniuta.services.impl;

import com.semeniuta.dao.ProductDao;
import com.semeniuta.dao.impl.ProductDaoImpl;
import com.semeniuta.domain.Product;
import com.semeniuta.services.ProductService;

import java.math.BigDecimal;
import java.util.List;

public class ProductServiceImpl implements ProductService{
    static ProductDao productDao = new ProductDaoImpl();

    @Override
    public boolean addProduct(String name, BigDecimal price) {
        Product product = new Product(name, price);
        return productDao.addProduct(product);
    }

    @Override
    public List<Product> showProducts() {
        return productDao.returnAllProducts();
    }

    @Override
    public boolean editProduct(String oldName, String name, BigDecimal price) {
        Product product = productDao.findProduct(oldName);
        return productDao.editProduct(product, name, price);
    }

    @Override
    public boolean deleteProduct(String name) {
        Product product = productDao.findProduct(name);
        return productDao.deleteProduct(product);
    }
}
