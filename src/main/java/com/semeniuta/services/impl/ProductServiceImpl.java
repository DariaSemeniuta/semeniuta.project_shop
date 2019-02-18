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
        return productDao.returnAllProducts();
    }

    @Override
    public boolean editProduct(long id, String name, BigDecimal price) {
        Product product = productDao.findProduct(id);
        return productDao.editProduct(product, name, price);
    }

    @Override
    public boolean deleteProduct(long id) {
        Product product = productDao.findProduct(id);
        return productDao.deleteProduct(product);
    }

    @Override
    public boolean isProductExist(long id) {
        if (productDao.findProduct(id).equals(null)) {
            return false;
        }
        return true;
    }

}
