package fr.afpa.ecommerce.bean;

import java.io.Serializable;

public class Author extends CommonTable implements Serializable {

    private String firstName;
    private String lastName;
    private String portrait;

    public Author() {
    }

    public Author(String firstName, String lastName, String portrait) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.portrait = portrait;
    }

    public Author(int id, String firstName, String lastName, String portrait) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.portrait = portrait;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

}
