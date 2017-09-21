package com.mrmarapps.helloinnocv.apiclient.request.UserRequest;

/**
 * Created by mario on 14/09/17.
 */

public class UserRequest {

    private int id;

    private String name;

    private String birthdate;


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
        return birthdate;
    }

    public void setBirthDate(String birthDate) {
        this.birthdate = birthDate;
    }
}
