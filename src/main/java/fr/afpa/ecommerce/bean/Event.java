package fr.afpa.ecommerce.bean;

import java.io.Serializable;

public class Event extends CommonTable implements Serializable{
    
    private String name;

    public Event() {
    }

    public Event(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
