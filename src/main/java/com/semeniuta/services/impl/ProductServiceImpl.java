package com.semeniuta.services.impl;

import com.semeniuta.dao.ProductDao;
import com.semeniuta.domain.Product;
import com.semeniuta.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private final ProductDao productDao;

    @Autowired
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
