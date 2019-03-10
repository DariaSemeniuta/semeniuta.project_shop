package com.semeniuta.services.impl;

import com.semeniuta.dao.ProductDao;
import com.semeniuta.domain.Product;
import com.semeniuta.services.ProductService;

import java.math.BigDecimal;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public boolean addProduct(String name, BigDecimal price) {
        Product product = new Product(name, price);
        return productDao.addProduct(product);
    }

    @Override
    public List<Product> showProducts() {
        return productDao.getAllProducts();
    }

    @Override
    public boolean editProduct(long id, String name, BigDecimal price) {
        return productDao.editProduct(id, name, price);
    }

    @Override
    public boolean deleteProduct(long id) {
        return productDao.deleteProduct(id);
    }

    @Override
    public boolean isProductExist(long id) {
        if (productDao.findProduct(id) == null) {
            return false;
        }
        return true;
    }

}
