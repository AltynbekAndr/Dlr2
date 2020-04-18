package com.springapp.mvc.model_for_users;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Shops")
public class Shops {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String login;
    private String password;
    private String address;
    private String photo;
    private String phone;
    private String latitude;
    private String longitude;



    @OneToMany(mappedBy = "shops",fetch = FetchType.LAZY)
    private List<UndGroups> undGroupses;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_group_us_id")
    private GroupsUs groupsus;
    @OneToMany(mappedBy = "shops", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<MyOrder> myOrders;

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

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public List<UndGroups> getUndGroupses() {
        return undGroupses;
    }

    public void setUndGroupses(List<UndGroups> undGroupses) {
        this.undGroupses = undGroupses;
    }

    public GroupsUs getGroupsus() {
        return groupsus;
    }

    public void setGroupsus(GroupsUs groupsus) {
        this.groupsus = groupsus;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<MyOrder> getMyOrders() {
        return myOrders;
    }

    public void setMyOrders(List<MyOrder> myOrders) {
        this.myOrders = myOrders;
    }


}

