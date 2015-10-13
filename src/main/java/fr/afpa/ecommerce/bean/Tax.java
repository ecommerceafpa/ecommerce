package fr.afpa.ecommerce.bean;

import java.io.Serializable;

public class Tax extends CommonTable implements Serializable{
    
    private String name;
    private float value;

    public Tax() {
    }

    public Tax(String name, float value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
      
}
