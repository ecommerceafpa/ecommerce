package fr.afpa.ecommerce.bean;

import java.io.Serializable;
import java.sql.Date;

public class BookEvent implements Serializable{
    
    private int id;
    private int bookId;
    private int eventId;
    private Date startDate;
    private Date endDate;

    public BookEvent() {
    }

    public BookEvent(int id, int bookId, int eventId, Date startDate, Date endDate) {
        this.id = id;
        this.bookId = bookId;
        this.eventId = eventId;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    
    
}
