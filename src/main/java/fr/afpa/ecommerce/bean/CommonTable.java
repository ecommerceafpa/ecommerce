package fr.afpa.ecommerce.bean;

import java.io.Serializable;
import java.util.Date;

public class CommonTable implements Serializable {

    protected int id;
    protected Date created;
    protected Date updated;
    protected boolean deleted;

    public CommonTable() {
    }

    public CommonTable(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

}
