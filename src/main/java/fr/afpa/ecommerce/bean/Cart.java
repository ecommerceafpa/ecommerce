package fr.afpa.ecommerce.bean;

import java.io.Serializable;

public class Cart extends CommonTable implements Serializable{
    
    private int customerId;
    private int addresseeId;
    private String sessionId;

    public Cart() {
    }

    public Cart(int customerId, int addresseeId, String sessionId) {
        this.customerId = customerId;
        this.addresseeId = addresseeId;
        this.sessionId = sessionId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getAddresseeId() {
        return addresseeId;
    }

    public void setAddresseeId(int addresseeId) {
        this.addresseeId = addresseeId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    
    
    
}
