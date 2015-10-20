package fr.afpa.ecommerce.bean;

import java.io.Serializable;

public class Category extends CommonTable implements Serializable {

    private String name;
    private String parent;
    private Integer parentCategoryId;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public Category(Integer id, String name, String parent, Integer parentCategoryId) {
        super(id);
        this.name = name;
        this.parent = parent;
        this.parentCategoryId = parentCategoryId;
    }

    public Category(String name, Integer parentCategoryId) {
        this.name = name;
        this.parentCategoryId = parentCategoryId;
    }

    public Category(Integer id, String name, Integer parentCategoryId) {
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

    public Integer getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Integer parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return String.format("Category ::: [id = %s, parentCategoryId = %s, name = %s, deleted = %s, created = %s, updated = %s]", id, parentCategoryId, name, deleted, created, updated);
    }

}
