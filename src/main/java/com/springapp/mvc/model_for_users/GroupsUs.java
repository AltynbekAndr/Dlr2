package com.springapp.mvc.model_for_users;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "GroupsUs")
public class GroupsUs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "groupsus",fetch = FetchType.LAZY)
    private List<Shops> shopses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Shops> getShopses() {
        return shopses;
    }

    public void setShopses(List<Shops> shopses) {
        this.shopses = shopses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
