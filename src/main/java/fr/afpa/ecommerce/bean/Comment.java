package fr.afpa.ecommerce.bean;

import java.io.Serializable;

public class Comment extends CommonTable implements Serializable{
    
    private int customerId;
    private int bookId;
    private String title;
    private String text;
    private byte rate; 

    public Comment() {
    }

    public Comment(int customerId, int bookId, String title, String text, byte rate) {
        this.customerId = customerId;
        this.bookId = bookId;
        this.title = title;
        this.text = text;
        this.rate = rate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte getRate() {
        return rate;
    }

    public void setRate(byte rate) {
        this.rate = rate;
    }
     
}
