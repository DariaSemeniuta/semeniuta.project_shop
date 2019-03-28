package com.semeniuta.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//TODO add enum for order statuses
@Entity
@Table(name="ORDERS")
public class Order {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;
    @Column(name = "status")
    private String status;

    @JoinTable(
            name = "ORDER_INFO",
            joinColumns = @JoinColumn(
                    name = "order_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "product_id",
                    referencedColumnName = "id"
            )
    )
    @OneToMany(targetEntity = Product.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    private List<Long> products;

    @Column(name = "client_id")
    private long idClient;

    public Order() {
    }

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
                ", \nproducts=" + getProducts().toString() +
                ", \nclientId=" + idClient +
                "}";
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
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
