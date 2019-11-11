package com.prakhar.model;

import com.google.api.client.util.Base64;
import org.apache.commons.lang3.StringUtils;
import com.prakhar.utils.PasswordUtils;

import javax.persistence.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@javax.persistence.Entity
@Table(name = "person")

public class Person extends Entity {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String firstName;

    @Column
    private String lastName;

    @Column(unique = true)
    private String email;

    @Column
    private String salt;

    @Column
    private String passwordHash;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "person", orphanRemoval = true)
    @OrderBy("id")
    private List<BillingAddress> billingAddressList = new ArrayList<>();

    public Person(String username, String firstName, String lastName, String email, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        setPassword(password);
    }

    public Person() {
    }

    private void setPassword(String password) {
        if(password!=null && !StringUtils.isBlank(password)) {
            setSalt();
            setHashedPassword(password);
        }
    }

    private void setSalt() {
        final Random r = new SecureRandom();
        byte[] salt = new byte[64];
        r.nextBytes(salt);
        this.salt = Base64.encodeBase64String(salt);
    }

    private void setHashedPassword(String password) {
        if (password != null && !StringUtils.isBlank(password)) {
            this.passwordHash = PasswordUtils.getHash(password.toCharArray(), salt.getBytes(), 256, 256);
        }
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public boolean authenticate(String password) {
        Boolean flag = Boolean.FALSE;
        if (password != null && !StringUtils.isBlank(password)) {
            String hashedPassword = PasswordUtils.getHash(password.toCharArray(), salt.getBytes(), 256, 256);
            if (hashedPassword.equals(this.passwordHash)) {
                flag = Boolean.TRUE;
            }
        }
        return flag;
    }

    public void addBillingAddress (BillingAddress billingAddress) {
        this.billingAddressList.add(billingAddress);
    }

    public List<BillingAddress> getBillingAddressList() {
        return billingAddressList;
    }

}
