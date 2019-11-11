package com.prakhar.response;

public class BillingAddressResponse {

    String addressLine1;
    String addressLine2;
    String city;
    String state;
    String pinCode;
    String country;
    String firstName;
    String lastName;
    String phoneNumber;

    public BillingAddressResponse() {
    }

    public BillingAddressResponse(String addressLine1, String addressLine2, String city, String state, String pinCode,
                                  String country, String firstName, String lastName, String phoneNumber) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
        this.country = country;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
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

    public String getCountry() {
        return country;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
