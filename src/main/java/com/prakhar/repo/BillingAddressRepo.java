package com.prakhar.repo;

import com.prakhar.model.BillingAddress;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class BillingAddressRepo extends AbstractRepo {

    public BillingAddressRepo(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<BillingAddress> getBillingAddressesForPerson (long personId) {
        Query query = currentSession().createQuery("FROM billingaddress " +
                "where personid = :personId");
        query.setParameter("personId", personId);
        return query.list();
    }
}
