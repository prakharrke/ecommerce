package com.prakhar.system;

import com.prakhar.auth.User;
import com.prakhar.model.Person;
import com.prakhar.repo.PersonRepo;
import org.glassfish.jersey.server.internal.inject.AbstractContainerRequestValueFactory;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.net.URI;

public class PersonFactory extends AbstractContainerRequestValueFactory<Person> {

    private PersonRepo personRepo = new PersonRepo(SessionFactoryHolder.getSessionFactory());

    @Override
    public Person provide() {
        try {
            User user = (User) getContainerRequest().getSecurityContext().getUserPrincipal();
            if (user == null) {
                return null;
            }
            return personRepo.findPersonByEmail(user.getEmail()).orElseThrow(() -> new WebApplicationException(Response.status(302).location(URI.create("/app/home")).build()));
        } catch (Exception ex) {
            LoggerFactory.getLogger(PersonFactory.class.getName()).info("Failed loading", ex);
        }

        throw new WebApplicationException(Response.status(302).location(URI.create("/app/home")).build());
    }
}
