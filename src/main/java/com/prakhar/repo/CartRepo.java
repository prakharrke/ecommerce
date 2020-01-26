package com.prakhar.repo;

import com.prakhar.model.CartItem;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.Optional;

public class CartRepo extends AbstractRepo {


    public CartRepo(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    public Optional<CartItem> findCartItemByCartAndProductId(Long cartId, Long productId) {
        Query query = currentSession().createQuery("from CartItem where cartId= :cartId and productId= :productId ");
        query.setParameter("cartId", cartId)
                .setParameter("productId", productId);

        return query.uniqueResultOptional();
    }

}
