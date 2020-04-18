package com.springapp.mvc.models2;



public class UndGroupsMin {
    private int id;
    private boolean shopCons;
    private float debt;

    private Groups2 groups2;
    private Shops2 shops2;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Shops2 getShops2() {
        return shops2;
    }

    public void setShops2(Shops2 shops2) {
        this.shops2 = shops2;
    }

    public Groups2 getGroups2() {
        return groups2;
    }

    public void setGroups2(Groups2 groups2) {
        this.groups2 = groups2;
    }
}
