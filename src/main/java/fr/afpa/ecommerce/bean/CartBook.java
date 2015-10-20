package fr.afpa.ecommerce.bean;

import java.io.Serializable;
import java.sql.Date;

public class CartBook implements Serializable{
    
    private Integer id;
    private Integer cartId;
    private Integer bookId;
    private Integer quantity;
    private Date dateAdd;

    public CartBook() {
    }

    public CartBook(Integer id, Integer cartId, Integer bookId, Integer quantity, Date dateAdd) {
        this.id = id;
        this.cartId = cartId;
        this.bookId = bookId;
        this.quantity = quantity;
        this.dateAdd = dateAdd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(Date dateAdd) {
        this.dateAdd = dateAdd;
    }
    
    
    
    
}
