package fr.afpa.ecommerce.bean;

import java.io.Serializable;

public class BookEvent implements Serializable {

    private Integer id;
    private Integer bookId;
    private Integer eventId;

    public BookEvent() {
    }

    public BookEvent(Integer id, Integer bookId, Integer eventId) {
        this.id = id;
        this.bookId = bookId;
        this.eventId = eventId;
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

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

}
