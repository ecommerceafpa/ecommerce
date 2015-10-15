package fr.afpa.ecommerce.bean;

import java.io.Serializable;

public class BookEvent implements Serializable {

    private int id;
    private int bookId;
    private int eventId;

    public BookEvent() {
    }

    public BookEvent(int id, int bookId, int eventId) {
        this.id = id;
        this.bookId = bookId;
        this.eventId = eventId;
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

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

}
