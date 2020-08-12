package com.prakhar.model;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "billingaddress")
public class BillingAddress extends Entity{

    @Column
    String country;

    @Column
    String addressLine1;

    @Column
    String addressLine2;

    @Column
    String city;

    @Column
    String state;

    @Column
    String pinCode;

    @Column
    String phone;

    @Column
    String firstName;

    @Column
    String lastName;

    @ManyToOne
    @JoinColumn(name = "personId")
    private Person person;


    public BillingAddress() {
    }

    public BillingAddress(String country, String addressLine1, String addressLine2, String city, String state,
                          String pinCode, String phone, String firstName, String lastName, Person person) {
        this.country = country;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.person = person;
    }


    public String getCountry() {
        return country;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPinCode() {
        return pinCode;
    }

    public String getPhone() {
        return phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Person getPerson() {
        return person;
    }

    public String toString() {
        return new StringBuilder().append(addressLine1)
                .append(", ")
                .append(addressLine2)
                .append(addressLine2.isEmpty() ? "" : ", ")
                .append(city)
                .append(", ")
                .append(state).toString();
    }
}


