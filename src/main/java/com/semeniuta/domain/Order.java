package com.semeniuta.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Order {
    private long id;
    private String status;
    private List<Long> products;

    public Order(List<Long> products) {
        this.status = "New";
        this.products = products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "\nid=" + id +
                ", \nstatus=" + status +
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

}
