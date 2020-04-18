package com.springapp.mvc.models2;

public class UndGroups2 {
    private int id;
    private int shopId;
    private int grId;
    private int compId;
    private boolean shopCons;
    private float debt;


    private Company2 company2;
    private Groups2 groups2;
    private Shops2 shops2;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Company2 getCompany2() {
        return company2;
    }

    public void setCompany2(Company2 company2) {
        this.company2 = company2;
    }

    public Groups2 getGroups2() {
        return groups2;
    }

    public void setGroups2(Groups2 groups2) {
        this.groups2 = groups2;
    }

    public Shops2 getShops2() {
        return shops2;
    }

    public void setShops2(Shops2 shops2) {
        this.shops2 = shops2;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getGrId() {
        return grId;
    }

    public void setGrId(int grId) {
        this.grId = grId;
    }

    public int getCompId() {
        return compId;
    }

    public void setCompId(int compId) {
        this.compId = compId;
    }

    public boolean isShopCons() {
        return shopCons;
    }

    public void setShopCons(boolean shopCons) {
        this.shopCons = shopCons;
    }

    public float getDebt() {
        return debt;
    }

    public void setDebt(float debt) {
        this.debt = debt;
    }
}
