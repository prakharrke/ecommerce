package prakhar.resources;


import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import prakhar.auth.User;
import prakhar.model.Person;
import prakhar.repo.PersonRepo;
import prakhar.utils.WebUtils;
import prakhar.web.HomePageView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Optional;

@Path("/")
public class HomeResource {

    private PersonRepo personRepo;

    public HomeResource(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @Path("home")
    @GET
    @UnitOfWork
    public HomePageView getHomePage(@Auth User user) {
        String personEmail = user.getEmail();
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
        Person person = personOptional.get();
        return new HomePageView("home.ftl", person);
    }
}
