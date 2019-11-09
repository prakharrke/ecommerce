package com.prakhar;

import com.google.common.collect.ImmutableList;
import com.prakhar.auth.AuthenticationFilter;
import com.prakhar.auth.JwtAuthenticator;
import com.prakhar.auth.User;
import com.prakhar.model.Person;
import com.prakhar.repo.PersonRepo;
import com.prakhar.resources.HomeResource;
import com.prakhar.resources.LoginWebResource;
import com.prakhar.system.Keys;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.flyway.FlywayBundle;
import io.dropwizard.flyway.FlywayFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.SessionFactoryFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.apache.commons.text.StrSubstitutor;
import org.flywaydb.core.Flyway;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ECommerceApplication extends Application<ECommerceConfiguration> {
    public static final ImmutableList<Class<?>> ENTITIES = ImmutableList.of(
            Person.class
    );

    public static void main(final String[] args) throws Exception {
        new ECommerceApplication().run(args);
    }

    private final HibernateBundle<ECommerceConfiguration> hibernate = new HibernateBundle<ECommerceConfiguration>(ENTITIES, new SessionFactoryFactory()) {
        @Override
        public DataSourceFactory getDataSourceFactory(ECommerceConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }

    };

    @Override
    public String getName() {
        return "ecommerce";
    }

    @Override
    public void initialize(final Bootstrap<ECommerceConfiguration> bootstrap) {

        String configPath = System.getenv("CONFIG_PATH");

        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        bootstrap.getConfigurationSourceProvider(),
                        new StrSubstitutor(loadConfigMapFromFile(configPath))));
        bootstrap.addBundle(new ViewBundle<>());
        bootstrap.addBundle(new AssetsBundle("/assets/", "/", "index.html"));
        bootstrap.addBundle(hibernate);


        bootstrap.addBundle(new FlywayBundle<ECommerceConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(ECommerceConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }

            @Override
            public FlywayFactory getFlywayFactory(ECommerceConfiguration configuration) {
                return configuration.getFlywayFactory();
            }
        });


    }

    @Override
    public void run(final ECommerceConfiguration configuration,
                    final Environment environment) throws IOException {

        // Running flyway migrate on every run
        Flyway flyway = new Flyway();
        flyway.setDataSource(configuration.getDataSourceFactory().getUrl(),
                configuration.getDataSourceFactory().getUser(),
                configuration.getDataSourceFactory().getPassword());
        flyway.migrate();

        // auth setup

        JwtAuthenticator jwtAuthenticator = new JwtAuthenticator(Keys.loadJwtKey());
        environment.jersey().register(new AuthDynamicFeature(
                new AuthenticationFilter.Builder<User>()
                        .setAuthenticator(jwtAuthenticator)
                        .setPrefix("Bearer")
                        .buildAuthFilter()));
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
        // * Register Repos
        PersonRepo personRepo = new PersonRepo(hibernate.getSessionFactory());

        // *  RegisterResources
        environment.jersey().register(
                new LoginWebResource(personRepo, jwtAuthenticator)
        );
        environment.jersey().register(
                new HomeResource(personRepo)
        );

    }

    private static Map<String, String> loadConfigMapFromFile(String configPath) {
        try {
            Properties variables = new Properties();
            variables.load(new FileInputStream(configPath));
            HashMap<String, String> map = new HashMap<>();
            variables.forEach((k, v) -> map.put((String) k, (String) v));
            return map;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
