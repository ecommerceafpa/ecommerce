package fr.afpa.ecommerce.bean;

import java.io.Serializable;

public class BookAuthor implements Serializable{
    
    private Integer id;
    private Integer bookId;
    private Integer authorId;

    public BookAuthor() {
    }

    public BookAuthor(Integer id, Integer bookId, Integer authorId) {
        this.id = id;
        this.bookId = bookId;
        this.authorId = authorId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }
    
    
}
