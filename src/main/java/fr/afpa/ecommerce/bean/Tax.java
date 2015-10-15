package fr.afpa.ecommerce.bean;

import java.io.Serializable;

public class Tax extends CommonTable implements Serializable {

    private String name;
    private Double value;

    public Tax() {
    }

    public Tax(String name, Double value) {
        this.name = name;
        this.value = value;
    }

    public Tax(int id, String name, Double value) {
        super(id);
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

}
