package com.springapp.mvc.model_for_users;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String login;
    private String password;
    private String address;
    private String photo;
    private String phone;
    @OneToMany(mappedBy = "company",fetch = FetchType.LAZY)
    private List<Products> products;

    @OneToMany(mappedBy = "company",fetch = FetchType.LAZY)
    private List<Courier> couriers;

    @OneToMany(mappedBy = "company",fetch = FetchType.LAZY)
    private List<MyOrder> orders;

    @OneToMany(mappedBy = "company",fetch = FetchType.LAZY)
    private List<Categories> categorieses;

    @OneToMany(mappedBy = "company",fetch = FetchType.LAZY)
    private List<Groups> groups;
    @OneToMany(mappedBy = "company",fetch = FetchType.LAZY)
    private List<UndGroups> undGroups;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<MyOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<MyOrder> orders) {
        this.orders = orders;
    }

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }

    public List<Courier> getCouriers() {
        return couriers;
    }

    public void setCouriers(List<Courier> couriers) {
        this.couriers = couriers;
    }

    public List<Categories> getCategorieses() {
        return categorieses;
    }

    public void setCategorieses(List<Categories> categorieses) {
        this.categorieses = categorieses;
    }

    public List<Groups> getGroups() {
        return groups;
    }

    public void setGroups(List<Groups> groups) {
        this.groups = groups;
    }

    public List<UndGroups> getUndGroups() {
        return undGroups;
    }

    public void setUndGroups(List<UndGroups> undGroups) {
        this.undGroups = undGroups;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}