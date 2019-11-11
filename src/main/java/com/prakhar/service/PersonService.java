package com.prakhar.service;

import com.prakhar.model.BillingAddress;
import com.prakhar.repo.BillingAddressRepo;
import com.prakhar.repo.PersonRepo;
import com.prakhar.response.BillingAddressResponse;

import java.util.List;

public class PersonService {

    PersonRepo personRepo;
    BillingAddressRepo billingAddressRepo;

    public PersonService(PersonRepo personRepo, BillingAddressRepo billingAddressRepo) {
        this.personRepo = personRepo;
        this.billingAddressRepo = billingAddressRepo;
    }

    public static BillingAddressResponse convertBillingAddress(BillingAddress billingAddress) {
        return new BillingAddressResponse(billingAddress.getAddressLine1(), billingAddress.getAddressLine2(),
                billingAddress.getCity(), billingAddress.getState(), billingAddress.getPinCode(), billingAddress.getCountry(),
                billingAddress.getFirstName(), billingAddress.getLastName(), billingAddress.getPhone());
    }

}
