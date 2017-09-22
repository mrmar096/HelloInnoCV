package com.mrmarapps.helloinnocv.fragmentdetailuser.viewmodel;

import java.util.Date;

/**
 * Created by mario on 14/09/17.
 */

public class UserDetail {

    private int id;

    private String name;

    private String birthDate;

    public UserDetail(int id, String name, String birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public UserDetail(String name, String birthDate) {
        this.name=name;
        this.birthDate=birthDate;
    }

    public UserDetail() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
