package fr.afpa.ecommerce.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class Tax extends CommonTable implements Serializable {

    private String name;
    private BigDecimal value;

    public Tax() {
    }

    public Tax(String name, BigDecimal value) {
        this.name = name;
        this.value = value;
    }

    public Tax(int id, String name, BigDecimal value) {
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

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

}
