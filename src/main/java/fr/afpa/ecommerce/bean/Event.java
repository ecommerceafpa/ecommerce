package fr.afpa.ecommerce.bean;

import java.io.Serializable;
import java.util.Date;

public class Event extends CommonTable implements Serializable {

    private String name;
    private Date startDate;
    private Date endDate;
    private Integer[] bookIds;

    public Event() {
    }

    public Event(String name) {
        this.name = name;
    }

    public Event(String name, Date startDate, Date endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Event(int id, String name, Date startDate, Date endDate) {
        super(id);
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer[] getBookIds() {
        return bookIds;
    }

    public void setBookIds(Integer[] bookIds) {
        this.bookIds = bookIds;
    }

}
