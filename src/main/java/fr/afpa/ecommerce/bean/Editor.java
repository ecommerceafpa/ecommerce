package fr.afpa.ecommerce.bean;

import java.io.Serializable;

public class Editor extends CommonTable implements Serializable{
    
    private String name;

    public Editor() {
    }

    public Editor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
  
}
