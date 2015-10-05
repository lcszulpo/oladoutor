package com.lcszulpo.oladoutor.model;

import java.util.List;

/**
 * Created by lcszulpo on 12/09/15.
 */
public class Clinic {

    private Integer id;
    private String description;
    private List<Locale> locales;

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

    public List<Locale> getLocales() {
        return locales;
    }

    public void setLocales(List<Locale> locales) {
        this.locales = locales;
    }

}
