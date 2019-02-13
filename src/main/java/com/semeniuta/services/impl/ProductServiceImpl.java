package com.semeniuta.services.impl;

import com.semeniuta.dao.ProductDao;
import com.semeniuta.dao.impl.ProductDaoImpl;
import com.semeniuta.domain.Product;
import com.semeniuta.services.ProductService;

import java.math.BigDecimal;
import java.util.List;

public class ProductServiceImpl implements ProductService{
    private final ProductDao productDao = new ProductDaoImpl();

    @Override
    public boolean addProduct(String name, BigDecimal price) {
        return false;
    }

    @Override
    public List<Product> showProducts() {
        return productDao.returnAllProducts();
    }

    @Override
    public boolean editProduct(long id) {
        return false;
    }

    @Override
    public boolean deleteProduct(long id) {
        return false;
    }
}
