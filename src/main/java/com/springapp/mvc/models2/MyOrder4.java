package com.springapp.mvc.models2;


public class MyOrder4 {
    private int id;
    private String date;
    private String sum;
    private String courierId;
    private String companyId;
    private String shopsId;

    public String getShopsId() {
        return shopsId;
    }

    public void setShopsId(String shopsId) {
        this.shopsId = shopsId;
    }

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

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getCourierId() {
        return courierId;
    }

    public void setCourierId(String courierId) {
        this.courierId = courierId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "MyOrder4{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", sum='" + sum + '\'' +
                ", courierId='" + courierId + '\'' +
                ", companyId='" + companyId + '\'' +
                '}';
    }
}
