package fr.afpa.ecommerce.bean;

import java.io.Serializable;

public class Language extends CommonTable implements Serializable{
    
    private String name;

    public Language() {
    }

    public Language(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
}
