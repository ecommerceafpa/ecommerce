package fr.afpa.ecommerce.bean;

import java.io.Serializable;
import java.util.Date;

public class CommonTable implements Serializable {

    protected Integer id;
    protected Date created;
    protected Date updated;
    protected Boolean deleted;

    public CommonTable() {
    }

    public CommonTable(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

}
