package com.springapp.mvc.model_for_users;


import javax.persistence.*;

@Entity
@Table(name = "UndGroups")
public class UndGroups {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private boolean shopCons;
    private float shopDebt;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groups_id")
    private Groups groups;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shops_id")
    private Shops shops;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Shops getShops() {
        return shops;
    }

    public void setShops(Shops shops) {
        this.shops = shops;
    }

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public boolean isShopCons() {
        return shopCons;
    }

    public void setShopCons(boolean shopCons) {
        this.shopCons = shopCons;
    }

    public float getShopDebt() {
        return shopDebt;
    }

    public void setShopDebt(float shopDebt) {
        this.shopDebt = shopDebt;
    }


}
