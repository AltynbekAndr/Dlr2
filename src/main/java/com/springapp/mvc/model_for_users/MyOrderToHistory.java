package com.springapp.mvc.model_for_users;

import java.util.List;


public class MyOrderToHistory {

    private int id;
    private String date;
    private String status;
    private String sum;
    private Courier courier;
    private Company company;
    private List<Products> productses;

    private Shops shops;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Products> getProductses() {
        return productses;
    }

    public void setProductses(List<Products> productses) {
        this.productses = productses;
    }

    public Shops getShops() {
        return shops;
    }

    public void setShops(Shops shops) {
        this.shops = shops;
    }
}