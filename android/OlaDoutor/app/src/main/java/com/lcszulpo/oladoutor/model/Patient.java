package com.lcszulpo.oladoutor.model;

import com.lcszulpo.oladoutor.model.Locale;

import java.io.Serializable;

/**
 * Created by lcszulpo on 12/09/15.
 */
public class Patient implements Serializable {

    private Integer id;
    private String name;
    private String lastName;
    private String age;
    private AgeType ageType;
    private Sex sex;
    private Locale locale;
    private Integer version;
    private Status status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public AgeType getAgeType() {
        return ageType;
    }

    public void setAgeType(AgeType ageType) {
        this.ageType = ageType;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum AgeType {
        YEARS, MONTHS
    }

    public enum Sex {
        M,F
    }

    public enum Status {
        ACTIVE, INACTIVE
    }

}
