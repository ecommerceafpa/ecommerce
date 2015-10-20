package fr.afpa.ecommerce.bean;

import java.io.Serializable;

public class Customer extends CommonTable implements Serializable {
   
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private Boolean disabled;

    public Customer() {
    }    

    public Customer(String firstName, String lastName, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    @Override
    public String toString() {
        return String.format("Customer ::: [id = %s, firstName = %s, lastName = %s, userName = %s, password = %s, disabled = %s, created = %s, updated = %s, deleted = %s]", 
                id, firstName, lastName, userName, password, disabled, created, updated, deleted); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    

}
