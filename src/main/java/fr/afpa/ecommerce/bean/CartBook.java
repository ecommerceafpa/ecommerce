package fr.afpa.ecommerce.bean;

import java.io.Serializable;
import java.sql.Date;

public class CartBook implements Serializable{
    
    private int id;
    private int cartId;
    private int bookId;
    private int quantity;
    private Date dateAdd;

    public CartBook() {
    }

    public CartBook(int id, int cartId, int bookId, int quantity, Date dateAdd) {
        this.id = id;
        this.cartId = cartId;
        this.bookId = bookId;
        this.quantity = quantity;
        this.dateAdd = dateAdd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(Date dateAdd) {
        this.dateAdd = dateAdd;
    }
    
    
    
    
}
