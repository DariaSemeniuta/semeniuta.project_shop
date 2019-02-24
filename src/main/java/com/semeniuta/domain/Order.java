package com.semeniuta.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//TODO add enum for order statuses
public class Order {
    private long id;
    private String status;
    private List<Long> products;
    private long idClient;

    public Order(List<Long> products, long idClient) {
        this.status = "New";
        this.products = products;
        this.idClient = idClient;
    }

    @Override
    public String toString() {
        return "Order{" +
                "\nid=" + id +
                ", \nstatus=" + status +
                ", \nproducts=" + products.toString() +
                ", \nclientId=" + idClient +
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
