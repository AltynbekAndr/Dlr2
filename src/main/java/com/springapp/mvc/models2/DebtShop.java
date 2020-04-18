package com.springapp.mvc.models2;

/**
 * Created by zverek on 30.03.2019.
 */
public class DebtShop {
    private float debt;
    private int compId;
    private int shopId;

    public float getDebt() {
        return debt;
    }

    public void setDebt(float debt) {
        this.debt = debt;
    }

    public int getCompId() {
        return compId;
    }

    public void setCompId(int compId) {
        this.compId = compId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }
}
