package com.prakhar.model;

import com.prakhar.repo.BillingAddressRepo;
import com.prakhar.repo.PersonRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BillingAddressTest {

    Person person;
    PersonRepo personRepo;
    BillingAddressRepo billingAddressRepo;
    @Before
    public void before(){
        person = mock(Person.class);
        personRepo = mock(PersonRepo.class);
        billingAddressRepo = mock(BillingAddressRepo.class);
    }

    @Test
    public void testBillingAddress(){
        BillingAddress billingAddress = mock(BillingAddress.class);
        List<BillingAddress> billingAddresses = new ArrayList<>();
        billingAddresses.add(billingAddress);
        when(billingAddressRepo.getBillingAddressesForPerson(person.getId())).thenReturn(billingAddresses);

        Assert.assertEquals(billingAddressRepo.getBillingAddressesForPerson(person.getId()).get(0), billingAddress);
    }
}
