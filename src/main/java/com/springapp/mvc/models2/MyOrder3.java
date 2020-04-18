package com.springapp.mvc.models2;



import java.util.List;

public class MyOrder3 {
    private int id;
    private String date;
    private String status;
    private String sum;
    private List<Products2> productses;
    private Courier2 courier2;
    private Company2 company2;
    private Shops2 shops2;

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

    public List<Products2> getProductses() {
        return productses;
    }

    public void setProductses(List<Products2> productses) {
        this.productses = productses;
    }

    public Courier2 getCourier2() {
        return courier2;
    }

    public void setCourier2(Courier2 courier2) {
        this.courier2 = courier2;
    }

    public Company2 getCompany2() {
        return company2;
    }

    public void setCompany2(Company2 company2) {
        this.company2 = company2;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public Shops2 getShops2() {
        return shops2;
    }

    public void setShops2(Shops2 shops2) {
        this.shops2 = shops2;
    }
}
