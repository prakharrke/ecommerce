package com.prakhar.system;

import org.glassfish.hk2.api.InjectionResolver;
import org.glassfish.hk2.api.TypeLiteral;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.spi.internal.ValueFactoryProvider;

import javax.inject.Singleton;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

public class ValueResolverFeature implements Feature {

    @Override
    public boolean configure(FeatureContext context) {
        context.register(new AbstractBinder() {
            @Override
            public void configure() {

                bind(PersonFactory.class).to(PersonFactory.class)
                        .in(Singleton.class);

                bind(PersonProvider.class)
                        .to(ValueFactoryProvider.class)
                        .in(Singleton.class);

                bind(PersonResolver.class)
                        .to(new TypeLiteral<InjectionResolver<PersonInjector>>() {
                        })
                        .in(Singleton.class);

            }
        });
        return true;
    }
}
