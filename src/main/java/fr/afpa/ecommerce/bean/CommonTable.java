package fr.afpa.ecommerce.bean;

import java.io.Serializable;
import java.util.Date;

public class CommonTable implements Serializable {

    private int id;
    private Date created;
    private Date updated;
    private boolean deleted;

    public CommonTable() {
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
