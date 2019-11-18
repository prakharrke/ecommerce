package com.prakhar.system;

import com.prakhar.model.Person;
import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.internal.inject.AbstractValueFactoryProvider;
import org.glassfish.jersey.server.internal.inject.MultivaluedParameterExtractorProvider;
import org.glassfish.jersey.server.model.Parameter;

import javax.inject.Inject;

public class PersonProvider extends AbstractValueFactoryProvider {

    private final PersonFactory personFactory;

    @Inject
    public PersonProvider(
            final MultivaluedParameterExtractorProvider extractorProvider,
            ServiceLocator locator,
            PersonFactory personFactory) {

        super(extractorProvider, locator, Parameter.Source.UNKNOWN);
        this.personFactory = personFactory;
    }

    @Override
    protected Factory<?> createValueFactory(Parameter parameter) {
        Class<?> paramType = parameter.getRawType();
        PersonInjector annotation = parameter.getAnnotation(PersonInjector.class);
        if (annotation != null && paramType.isAssignableFrom(Person.class)) {
            return personFactory;
        }
        return null;
    }
}

