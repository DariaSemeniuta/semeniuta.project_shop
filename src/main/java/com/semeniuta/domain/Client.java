package com.semeniuta.domain;

public class Client {

    private long id;
    private String name;
    private String surname;
    private int age;
    private String email;
    private String phone;

    @Override
    public String toString() {
        return "Client{" +
                "\nid=" + id +
                ", \nname='" + name +
                ", \nsurname='" + surname +
                ", \nage=" + age +
                ", \nemail='" + email +
                ", \nphone='" + phone +
                "\n}";
    }

    public Client(String name, String surname, int age, String email, String phone) {
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
}
