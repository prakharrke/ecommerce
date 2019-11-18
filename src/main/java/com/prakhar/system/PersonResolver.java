package com.prakhar.system;

import org.glassfish.jersey.server.internal.inject.ParamInjectionResolver;

public class PersonResolver extends ParamInjectionResolver<PersonInjector> {
    public PersonResolver() {
        super(PersonProvider.class);
    }
}
