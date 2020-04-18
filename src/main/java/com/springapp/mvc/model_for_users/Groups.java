package com.springapp.mvc.model_for_users;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Groups")
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String priceGroup;

    private boolean pn;
    private boolean vt;
    private boolean sr;
    private boolean ch;
    private boolean pt;
    private boolean sb;
    private boolean vs;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_company_id")
    private Company company;

    @OneToMany(mappedBy = "groups",fetch = FetchType.LAZY)
    private List<UndGroups> undGroupses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<UndGroups> getUndGroupses() {
        return undGroupses;
    }

    public void setUndGroupses(List<UndGroups> undGroupses) {
        this.undGroupses = undGroupses;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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
}
