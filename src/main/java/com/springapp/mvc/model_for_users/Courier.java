package com.springapp.mvc.model_for_users;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Courier")
public class Courier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courier_id")
    private int id;
    private String name;
    private String l_name;
    @Column(unique = true)
    private String login;
    private String password;
    private String photo;
    private String phone;
    private String address;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courier_company_id")
    private Company company;

    @OneToMany(mappedBy = "courier",fetch = FetchType.LAZY)
    private List<MyOrder> orders;

    @OneToMany(mappedBy = "courier",fetch = FetchType.LAZY)
    private List<Products> productses;


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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public List<Products> getProductses() {
        return productses;
    }

    public void setProductses(List<Products> productses) {
        this.productses = productses;
    }
}
