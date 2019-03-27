package com.semeniuta.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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

    /*@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "order_info",
            joinColumns={@JoinColumn(name = "order_id")},
            inverseJoinColumns={@JoinColumn(name = "product_id")})*/
    //@OneToMany(mappedBy = "product_id", orphanRemoval = true)
    @OneToMany
    @JoinTable(name = "order_info", joinColumns = {@JoinColumn(name="order_id")},
            inverseJoinColumns = {@JoinColumn(name="product_id")} )
    private List<Long> products;
    @ManyToOne(targetEntity = Client.class)
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
