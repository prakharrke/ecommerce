package com.prakhar.resources;


import com.prakhar.model.Product;
import com.prakhar.repo.ProductRepo;
import com.prakhar.system.PersonInjector;
import com.prakhar.web.BillingDetailsView;
import com.prakhar.web.ProductDetailView;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import com.prakhar.auth.User;
import com.prakhar.model.Person;
import com.prakhar.repo.PersonRepo;
import com.prakhar.utils.WebUtils;
import com.prakhar.web.HomePageView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Optional;

@Path("/")
public class HomeResource {

    private PersonRepo personRepo;
    private ProductRepo productRepo;
    public HomeResource(PersonRepo personRepo, ProductRepo productRepo) {
        this.personRepo = personRepo;
        this.productRepo = productRepo;
    }

    @Path("home")
    @GET
    @UnitOfWork
    public HomePageView getHomePage(@Auth Optional<User> userOptional) {
        Person person=null;
        if(userOptional.isPresent()){
            String personEmail = userOptional.get().getEmail();
            Optional<Person> personOptional = personRepo.findPersonByEmail(personEmail);
            if (!personOptional.isPresent()) {
                throw new WebApplicationException(
                        Response.status(302).location(
                                URI.create("/app/accountServices/login")
                        ).cookie(
                                WebUtils.createNewCookie("token", "")
                        ).build()
                );
            }
            person = personOptional.get();
        }

        return new HomePageView("home.ftl", person, productRepo);
    }

    @Path("/billing")
    @GET
    @UnitOfWork
    public BillingDetailsView getProileView(@Auth User user) {
        String personEmail = user.getEmail();
        Optional<Person> personOptional = personRepo.findPersonByEmail(personEmail);
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
        return new BillingDetailsView("billingDetails.ftl", person);
    }

    @Path("/product/{productId}")
    @GET
    @UnitOfWork
    public ProductDetailView getProductDetails( @Auth Optional<User> userOptional, @PathParam("productId") Long productId) {
        Optional<Product> productOptional = productRepo.findProductById(productId);
        if(!productOptional.isPresent()) {
            throw new WebApplicationException(
                    Response.status(302).location(
                            URI.create("/app/home")
                    ).build()
            );
        }
        Product product = productOptional.get();
        return new ProductDetailView("productDetail.ftl", product);
    }
}
