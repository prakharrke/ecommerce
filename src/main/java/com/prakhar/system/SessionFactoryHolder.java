package com.prakhar.system;

import org.hibernate.SessionFactory;

public class SessionFactoryHolder {

    private static SessionFactory sessionFactory;

    private SessionFactoryHolder() {
    }

    public static void initialize(SessionFactory sessionFactory) {
        SessionFactoryHolder.sessionFactory = sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
