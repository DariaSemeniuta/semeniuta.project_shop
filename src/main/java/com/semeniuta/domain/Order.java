package com.semeniuta.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    public Order(List<Long> products, String status, long idClient) {
        this.status = status;
        this.products = products;
        this.idClient = idClient;
    }

    public Order(long id, String status, List<Long> products, long idClient) {
        this.id = id;
        this.status = status;
        this.products = products;
        this.idClient = idClient;
    }

    public List<Long> getProducts() {
        return products;
    }

    public void setProducts(List<Long> products) {
        this.products = products;
    }

    public long getIdClient() {
        return idClient;
    }

    public void setIdClient(long idClient) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getId() == order.getId() &&
                getIdClient() == order.getIdClient() &&
                Objects.equals(getStatus(), order.getStatus()) &&
                Objects.equals(getProducts(), order.getProducts());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getStatus(), getProducts(), getIdClient());
    }
}
