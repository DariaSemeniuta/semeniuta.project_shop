package com.semeniuta.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Order {
    private long id;
    private String status;
    private List<Long> productIds;

    public Order(long productId) {
        this.status = "New";
        productIds = new ArrayList<>();
        productIds.add(productId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "\nid=" + id +
                ", \nstatus='" + status +
                ", \nproductIds=" + productIds +
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

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }
}
