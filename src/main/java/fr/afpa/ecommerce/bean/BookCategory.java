package fr.afpa.ecommerce.bean;

import java.io.Serializable;

public class BookCategory implements Serializable{
    
    private int id;
    private int bookId;
    private int categoryId;

    public BookCategory() {
    }

    public BookCategory(int id, int bookId, int categoryId) {
        this.id = id;
        this.bookId = bookId;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    
    
    
}
