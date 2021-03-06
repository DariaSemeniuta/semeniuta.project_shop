package com.semeniuta.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
@Entity
@Table(name="CLIENTS")
public class Client {

    public Client() {
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private long id;

    @Column(name = "NAME")
    private String name;
    @Column(name = "SURNAME")
    private String surname;
    @Column(name = "AGE")
    private int age;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PHONE")
    private String phone;

    @Override
    public String toString() {
        return "Client{" +
                "\nid=" + id +
                ", \nname=" + name +
                ", \nsurname=" + surname +
                ", \nage=" + age +
                ", \nemail=" + email +
                ", \nphone=" + phone +
                "\n}";
    }

    public Client(String name, String surname, int age, String email, String phone) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.phone = phone;
    }

    public Client(long id, String name, String surname, int age, String email, String phone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.phone = phone;
    }


    public Client(String name, String phone) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getId() {

        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return getId() == client.getId() &&
                getAge() == client.getAge() &&
                Objects.equals(getName(), client.getName()) &&
                Objects.equals(getSurname(), client.getSurname()) &&
                Objects.equals(getEmail(), client.getEmail()) &&
                Objects.equals(getPhone(), client.getPhone());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName(), getSurname(), getAge(), getEmail(), getPhone());
    }
}
