package com.mrmarapps.helloinnocv.fragmentlistuser.viewmodel;

import java.util.Date;

/**
 * Created by mario on 14/09/17.
 */

public class User{

    private int id;

    private String name;

    private Date birthDate;


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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
