package com.lcszulpo.oladoutor.model;

import java.io.Serializable;

/**
 * Created by lcszulpo on 12/09/15.
 */
public class Locale implements Serializable {

    private Integer id;
    private String description;
    private Integer version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return description;
    }
}
