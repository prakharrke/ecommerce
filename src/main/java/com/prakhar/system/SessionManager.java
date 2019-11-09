package com.prakhar.system;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.context.internal.ManagedSessionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.concurrent.Callable;

public class SessionManager {
    private SessionFactory sessionFactory;

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionManager.class);

    public SessionManager(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void runInsideSessionContext(Runnable runnable) {
        runInsideSessionContext(() -> {
            runnable.run();
            return null;
        });
    }

    public void associateNewSession() {
        detachSession();
        attachSession();
    }

    public void attachSession() {
        try {
            sessionFactory.getCurrentSession();
        } catch (Throwable t) {
            // In case session is present we are good.
            Session session = sessionFactory.openSession();
            ManagedSessionContext.bind(session);
        }
    }

    public void detachSession() {
        try {
            sessionFactory.getCurrentSession().flush();
            sessionFactory.getCurrentSession().close();
            ManagedSessionContext.unbind(sessionFactory);
        } catch (HibernateException e) {
            // In case session is present we are good.

        }
    }

    public static void safely(Runnable runnable) {
        try {
            runnable.run();
        } catch (Throwable throwable) {
            // Ignore
        }
    }

    public void inTransaction(Runnable runnable) {
        inTransaction(() -> {
            runnable.run();
            return null;
        });
    }

    @Deprecated
    /**
     * @deprecated Please use inTransaction
     */
    public <T> Optional<T> runInsideSessionContext(Callable<T> call) {
        try {
            sessionFactory.getCurrentSession();
            throw new RuntimeException("There should be no session associated at this point");
        } catch (HibernateException e) {
            //everything is fine expecting no current session
        }
        Session session = sessionFactory.openSession();
        ManagedSessionContext.bind(session);
        Transaction transaction = session.beginTransaction();
        try {
            // As per https://stackoverflow.com/questions/1969109/why-hibernate-session-close-does-not-flushes-the-data-automatically
            final T result = call.call();
            session.flush();
            transaction.commit();
            return Optional.ofNullable(result);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
            ManagedSessionContext.unbind(sessionFactory);
        }
    }



    public <T> T inTransaction(Callable<T> call) {
        try {
            sessionFactory.getCurrentSession();
            throw new RuntimeException("There should be no session associated at this point");
        } catch (HibernateException e) {
            //everything is fine expecting no current session
        }
        Session session = sessionFactory.openSession();
        ManagedSessionContext.bind(session);
        Transaction transaction = session.beginTransaction();
        try {
            // As per https://stackoverflow.com/questions/1969109/why-hibernate-session-close-does-not-flushes-the-data-automatically
            final T result = call.call();
            session.flush();
            transaction.commit();
            return result;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            safely(session::close);
            safely(() -> ManagedSessionContext.unbind(sessionFactory));
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void rollbackTransaction() {
        sessionFactory.getCurrentSession().getTransaction().rollback();
    }
}
