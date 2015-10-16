package fr.afpa.ecommerce.bean;

import java.io.Serializable;

public class Category extends CommonTable implements Serializable {

    private String name;
    private int parentCategoryId;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(int id, String name) {
        super(id);
        this.name = name;
    }

    public Category(String name, int parentCategoryId) {
        this.name = name;
        this.parentCategoryId = parentCategoryId;
    }

    public Category(int id, String name, int parentCategoryId) {
        super(id);
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

    @Override
    public String toString() {
        return String.format("Category ::: [id = %s, parentCategoryId = %s, name = %s, deleted = %s, created = %s, updated = %s]", id, parentCategoryId, name, deleted, created, updated);
    }

}
