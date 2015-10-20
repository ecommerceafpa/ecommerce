package fr.afpa.ecommerce.bean;

import java.io.Serializable;

public class Cart extends CommonTable implements Serializable{
    
    private Integer customerId;
    private Integer addresseeId;
    private String sessionId;

    public Cart() {
    }

    public Cart(Integer customerId, Integer addresseeId, String sessionId) {
        this.customerId = customerId;
        this.addresseeId = addresseeId;
        this.sessionId = sessionId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getAddresseeId() {
        return addresseeId;
    }

    public void setAddresseeId(Integer addresseeId) {
        this.addresseeId = addresseeId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    
    
    
}
