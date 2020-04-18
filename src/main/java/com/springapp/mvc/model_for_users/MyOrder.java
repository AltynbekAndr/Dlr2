package com.springapp.mvc.model_for_users;

import javax.persistence.*;
import java.util.List;
@Entity(name = "MyOrder")
@Table(name = "myorder")
public class MyOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "myOrder_id")
    private int id;
    private String date;
    private String date_hms;
    private String status;
    private String sum;
    private boolean selected;
    private boolean hide;
    private boolean removeorder;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courier_id")
    private Courier courier;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "myorder_company_id")
    private Company company;
    @OneToMany(mappedBy = "myorder", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Products> productses;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shops_id")
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

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public Shops getShops() {
        return shops;
    }

    public void setShops(Shops shops) {
        this.shops = shops;
    }

    public String getDate_hms() {
        return date_hms;
    }

    public void setDate_hms(String date_hms) {
        this.date_hms = date_hms;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    public boolean isRemoveorder() {
        return removeorder;
    }

    public void setRemoveorder(boolean removeorder) {
        this.removeorder = removeorder;
    }
}
