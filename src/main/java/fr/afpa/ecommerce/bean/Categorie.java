package fr.afpa.ecommerce.bean;

import java.io.Serializable;

public class Categorie extends CommonTable implements Serializable{
    
    private String name;
    private int parentCategoryId;

    public Categorie() {
    }

    public Categorie(String name, int parentCategoryId) {
        this.name = name;
        this.parentCategoryId = parentCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(int parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }
    
    
}
