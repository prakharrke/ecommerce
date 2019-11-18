package com.prakhar.auth;

import io.dropwizard.auth.AuthFilter;
import io.dropwizard.auth.Authenticator;
import org.apache.http.HttpHeaders;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.security.Principal;

@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter<P extends Principal> extends AuthFilter<String, P> {
    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {

        String credentials = null;
        Cookie tokenCookie = containerRequestContext.getCookies().get("token");

        if (tokenCookie != null) {
            credentials = tokenCookie.getValue();
            if (credentials == null || !authenticate(containerRequestContext, credentials, SecurityContext.BASIC_AUTH)) {
                throw new WebApplicationException(
                        Response.status(302).location(
                                UriBuilder.fromPath("/app/accountServices/login").build()
                        ).build()
                );
            }
        } else {
            credentials = getCredentialsFromHeader(containerRequestContext.getHeaders().getFirst(HttpHeaders.AUTHORIZATION));
            if (credentials == null || !authenticate(containerRequestContext, credentials, SecurityContext.BASIC_AUTH)) {
                throw new WebApplicationException(
                        Response.status(302).location(
                                UriBuilder.fromPath("/app/accountServices/login").build()
                        ).build()
                );
            }
        }

        Principal principal = containerRequestContext.getSecurityContext().getUserPrincipal();
        if(containerRequestContext.getUriInfo().getPath().contains("admin")){
            User user = (User) principal;
            if(!user.getEmail().equalsIgnoreCase("prakhar.rke24@gmail.com") && !user.getEmail().equalsIgnoreCase("mukul.dixit@gmail.com")) {
                throw new WebApplicationException(
                        Response.status(302).location(
                                UriBuilder.fromPath("/app/home").build()
                        ).build()
                );
            }
        }
    }

    private String getCredentialsFromHeader(String header) {
        if (header == null) {
            return null;
        }

        int space = header.indexOf(" ");
        if (space <= 0) {
            return null;

        }

        if (!"Basic".equalsIgnoreCase(header.substring(0, space)) && !"Bearer".equalsIgnoreCase(header.substring(0, space))) {
            return null;
        }

        return header.substring(space + 1);

    }

    /**
     * Builder for {@link AuthenticationFilter}.
     * <p>An {@link Authenticator} must be provided during the building process.</p>
     *
     * @param <P> the type of the principal
     */
    public static class Builder<P extends Principal>
            extends AuthFilterBuilder<String, P, AuthenticationFilter<P>> {

        @Override
        protected AuthenticationFilter<P> newInstance() {
            return new AuthenticationFilter<>();
        }
    }
}
