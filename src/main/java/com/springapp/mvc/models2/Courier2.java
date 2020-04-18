package com.springapp.mvc.models2;


public class Courier2 {
    private int id;
    private String name;
    private String l_name;
    private String address;
    private String login;
    private String password;
    private String photo;
    private String phone;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Courier2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", l_name='" + l_name + '\'' +
                ", address='" + address + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", photo='" + photo + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
