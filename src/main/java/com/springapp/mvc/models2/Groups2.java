package com.springapp.mvc.models2;


public class Groups2 {
    private int id;
    private String name;
    private String priceGroup;
    private String shopImg;
    private boolean pn;
    private boolean vt;
    private boolean sr;
    private boolean ch;
    private boolean pt;
    private boolean sb;
    private boolean vs;
    private boolean shopCons;


    private Company2 company2;
    /*private List<UndGroups2> undGroupses2;*/
    private UndGroups2 undGroups;

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

    public String getPriceGroup() {
        return priceGroup;
    }

    public void setPriceGroup(String priceGroup) {
        this.priceGroup = priceGroup;
    }

    public boolean isPn() {
        return pn;
    }

    public void setPn(boolean pn) {
        this.pn = pn;
    }

    public boolean isVt() {
        return vt;
    }

    public void setVt(boolean vt) {
        this.vt = vt;
    }

    public boolean isSr() {
        return sr;
    }

    public void setSr(boolean sr) {
        this.sr = sr;
    }

    public boolean isCh() {
        return ch;
    }

    public void setCh(boolean ch) {
        this.ch = ch;
    }

    public boolean isPt() {
        return pt;
    }

    public void setPt(boolean pt) {
        this.pt = pt;
    }

    public boolean isSb() {
        return sb;
    }

    public void setSb(boolean sb) {
        this.sb = sb;
    }

    public boolean isVs() {
        return vs;
    }

    public void setVs(boolean vs) {
        this.vs = vs;
    }

    public Company2 getCompany2() {
        return company2;
    }

    public void setCompany2(Company2 company2) {
        this.company2 = company2;
    }

    public String getShopImg() {
        return shopImg;
    }

    public void setShopImg(String shopImg) {
        this.shopImg = shopImg;
    }

    public UndGroups2 getUndGroups() {
        return undGroups;
    }

    public void setUndGroups(UndGroups2 undGroups) {
        this.undGroups = undGroups;
    }

    public boolean isShopCons() {
        return shopCons;
    }

    public void setShopCons(boolean shopCons) {
        this.shopCons = shopCons;
    }
}
