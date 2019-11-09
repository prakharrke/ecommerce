package com.prakhar.auth;

import java.security.Principal;

public class User implements Principal {

    private String email;
    private String name;
    private Long personId;


    public User(String email, long personId) {
        this.email = email;
        this.personId = personId;
    }

    public String getEmail() {
        return email;
    }

    public Long getPersonId() {
        return personId;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
