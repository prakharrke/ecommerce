package com.prakhar.resources;

import com.prakhar.auth.User;
import com.prakhar.model.BillingAddress;
import com.prakhar.model.Person;
import com.prakhar.repo.BillingAddressRepo;
import com.prakhar.repo.PersonRepo;
import com.prakhar.response.BillingAddressResponse;
import com.prakhar.service.PersonService;
import com.prakhar.utils.WebUtils;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Path("/billing-details")
public class BillingResource {

    PersonRepo personRepo;
    BillingAddressRepo billingAddressRepo;

    public BillingResource(PersonRepo personRepo, BillingAddressRepo billingAddressRepo) {
        this.personRepo = personRepo;
        this.billingAddressRepo = billingAddressRepo;
    }

    @Path("add-address")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @UnitOfWork
    public Response addBillingAddress(@Auth User user, @FormParam("country") String country,
                                      @FormParam("addressLine1") String addressLine1,
                                      @FormParam("addressLine2") String addressLine2,
                                      @FormParam("city") String city,
                                      @FormParam("state") String state,
                                      @FormParam("pinCode") String pinCode,
                                      @FormParam("firstName") String firstName,
                                      @FormParam("lastName") String lastName,
                                      @FormParam("phone") String phone) {

        Optional<Person> personOptional = personRepo.findPersonByEmail(user.getEmail());
        if(!personOptional.isPresent()) {
            throw new WebApplicationException(
                    Response.status(302).location(
                            URI.create("/app/accountServices/login")
                    ).cookie(
                            WebUtils.createNewCookie("token", "")
                    ).build()
            );
        }

        Person person = personOptional.get();
        BillingAddress billingAddress = new BillingAddress(country, addressLine1, addressLine2, city, state, pinCode,
                phone, firstName, lastName, person);
        person.addBillingAddress(billingAddress);
        try {
            personRepo.save(person);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return Response.status(302).location(
                URI.create("/app/billing")
        ).build();
    }

    @GET
    @Path("/getAddresses")
    @UnitOfWork
    public List<BillingAddressResponse> getAddresses (@Auth User user) {
        Optional<Person> personOptional = personRepo.findPersonByEmail(user.getEmail());
        if(!personOptional.isPresent()) {
            throw new WebApplicationException(
                    Response.status(302).location(
                            URI.create("/app/accountServices/login")
                    ).cookie(
                            WebUtils.createNewCookie("token", "")
                    ).build()
            );
        }
        Person person = personOptional.get();
        List<BillingAddress> billingAddressList = billingAddressRepo.getBillingAddressesForPerson(person.getId());
        return billingAddressList.stream().map(PersonService::convertBillingAddress).collect(Collectors.toList());
    }
}
