package com.springapp.mvc.models2;

import com.springapp.mvc.model_for_users.Categories;
import com.springapp.mvc.model_for_users.Company;
import com.springapp.mvc.model_for_users.MyOrder;



public class Products2 {
    private Long id;
    private String name;
    private String price;
    private String weight;
    private String description;
    private String photo;
    private String amount;
    private String returnAmount;
    private String rec_price;

    private boolean debt;
    private boolean returned;
    private boolean consignation;
    private boolean productAccepted;
    private String acceptAmount;

    private Company company;

    private Categories categories;

    private MyOrder myOrder;

    public boolean isDebt() {
        return debt;
    }

    public void setDebt(boolean debt) {
        this.debt = debt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public MyOrder getMyOrder() {
        return myOrder;
    }

    public void setMyOrder(MyOrder myOrder) {
        this.myOrder = myOrder;
    }

    public boolean isConsignation() {
        return consignation;
    }

    public void setConsignation(boolean consignation) {
        this.consignation = consignation;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public String getRec_price() {
        return rec_price;
    }

    public void setRec_price(String rec_price) {
        this.rec_price = rec_price;
    }

    public String getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(String returnAmount) {
        this.returnAmount = returnAmount;
    }

    public boolean isProductAccepted() {
        return productAccepted;
    }

    public void setProductAccepted(boolean productAccepted) {
        this.productAccepted = productAccepted;
    }

    public String getAcceptAmount() {
        return acceptAmount;
    }

    public void setAcceptAmount(String acceptAmount) {
        this.acceptAmount = acceptAmount;
    }
}
