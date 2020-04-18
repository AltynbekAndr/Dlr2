package com.springapp.mvc.model_for_users;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Categories")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String category;

    @OneToMany(mappedBy = "categories",fetch = FetchType.LAZY)
    private List<Products> products;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categorieses_company_id")
    private Company company;

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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }
}
