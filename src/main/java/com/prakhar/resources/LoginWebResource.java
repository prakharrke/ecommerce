package com.prakhar.resources;

import io.dropwizard.hibernate.UnitOfWork;
import com.prakhar.auth.JwtAuthenticator;
import com.prakhar.model.Person;
import com.prakhar.repo.PersonRepo;
import com.prakhar.utils.WebUtils;
import com.prakhar.web.LoginView;
import com.prakhar.web.SignUpView;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Optional;

@Path("/accountServices")
public class LoginWebResource {

    PersonRepo personRepo;
    JwtAuthenticator jwtAuthenticator;
    public LoginWebResource (PersonRepo personRepo, JwtAuthenticator jwtAuthenticator) {

        this.personRepo = personRepo;
        this.jwtAuthenticator = jwtAuthenticator;
    }

    @Path("/v1/api/signup")
    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response signUp(@FormParam("email") String email,
                           @FormParam("password") String password,
                           @FormParam("firstName") String firstName,
                           @FormParam("lastName") String lastName) {
        Optional<Person> personOptional = personRepo.findPersonByEmail(email);
        if(personOptional.isPresent()) {
            return Response.status(409).build();
        }

        Person person = new Person(email, firstName, lastName, email, password);
        personRepo.save(person);
        String jwtToken = jwtAuthenticator.generateJwtToken(person);
        return Response.status(302).location(
                URI.create("/app/home")).
                cookie(WebUtils.createNewCookie("token", jwtToken)).
                build();
    }


    @Path("/v1/api/signin")
    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response signIn(@FormParam("email") String email,
                           @FormParam("password") String password) {
        Optional<Person> personOptional = personRepo.findPersonByEmail(email);
        if(!personOptional.isPresent()) {
            throw new WebApplicationException(
                    Response.status(302).location(
                            UriBuilder.fromPath("/app/accountServices/login").queryParam(
                                    "message", "unregistered"
                            ).build()
                    ).build()
            );
        }

        Person person = personOptional.get();
        if(!person.authenticate(password)) {
            return Response.status(302).location(
                    UriBuilder.fromPath("/app/accountServices/login").queryParam(
                            "message", "invalid.credentials"
                    ).build()
            ).build();
        }

        String jwtToken = jwtAuthenticator.generateJwtToken(person);
        return Response.status(302).location(
                URI.create("/app/home")
        ).cookie(WebUtils.createNewCookie("token", jwtToken)).build();

    }

    @Path("/sign-up")
    @GET
    public SignUpView signUpView() {

        return new SignUpView("signup.ftl");
    }

    @Path("/login")
    @GET
    public LoginView login(@QueryParam("message") String message) {
        return new LoginView("login.ftl", message);
    }

}
