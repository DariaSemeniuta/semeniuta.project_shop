package com.semeniuta.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Order {
    private long id;
    private String status;
    private List<Product> products;

    private static long idCounter = 0;


    public Order(Product product) {
        this.id = ++idCounter;
        this.status = "New";
        products = new ArrayList<>();
        products.add(product);

    }

    @Override
    public String toString() {
        return "Order{" +
                "\nid=" + id +
                ", \nstatus='" + status +
                ", \nproducts=" + products.toString() +
                "}";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public void deleteProduct(Product product){
        products.remove(product);
    }
}
