package com.springapp.mvc.models2;



public class Categories2 {

    private int id;
    private String category;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    @Override
    public String toString() {
        return "Categories2{" +
                "id=" + id +
                ", category='" + category + '\'' +
                '}';
    }
}
