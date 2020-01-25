package com.prakhar.repo;

import com.prakhar.model.Cart;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import com.prakhar.model.Person;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Optional;

public class PersonRepo extends AbstractRepo<Person> {

    public PersonRepo(SessionFactory factory) {
        super(factory);
    }

    public Optional<Person> findPersonByEmail(String email) {
        CriteriaBuilder criteriaBuilder = currentSession().getCriteriaBuilder();
        CriteriaQuery<Person> criteriaQuery = criteriaBuilder.createQuery(Person.class);
        Root<Person> root = criteriaQuery.from(Person.class);

        criteriaQuery.select(root).where(
                criteriaBuilder.equal(root.get("email"), email)
        );
        Query<Person> query = currentSession().createQuery(criteriaQuery);
        return query.uniqueResultOptional();
    }

    public Cart getCartFromPersonId(Person person) {
        Query query = currentSession().createQuery("from Cart where personId = :id");
        query.setParameter("id", person.getId());
        Optional<Cart> optionalCart = query.uniqueResultOptional();
        return optionalCart.orElseGet(() -> person.setCart(new Cart(person)));
    }

}
