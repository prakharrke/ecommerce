package com.prakhar.helpers;

import org.hibernate.SessionFactory;
import org.jvnet.hk2.annotations.Service;

@Service
public class TruncateDatabaseService {

    /**
     * Tables are deleted in the following order to ensure that foreign key dependencies
     * do not cause deletions in tests to fail.
     * <p>
     * If a test is failing because a child row exists when a master row is deleted in code,
     * try adding the child entity to the top of the list.
     */
    public static final String[] TABLE_NAMES = new String[]{
            "person"
    };

    public static final String[] OBJECT_NAMES = new String[]{
            "Person"

    };

    public static void deleteTables(SessionFactory sessionFactory) {

        for (String tableName : TABLE_NAMES) {
            String queryString = "DELETE FROM " + tableName;
            sessionFactory.getCurrentSession().createSQLQuery(queryString).executeUpdate();
        }
        for (String tableName : OBJECT_NAMES) {
            String queryString = "DELETE FROM " + tableName;
            sessionFactory.getCurrentSession().createSQLQuery(queryString).executeUpdate();
        }
    }
}
