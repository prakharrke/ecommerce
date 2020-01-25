package com.prakhar.repo;

import com.prakhar.model.Cart;
import com.prakhar.model.CartItem;
import com.prakhar.model.Product;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Optional;

public class CartRepo extends AbstractRepo {


    public CartRepo(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    private Optional<CartItem> findCartItemByCartAndProductId(Long cartId, Long productId) {
        Query query = currentSession().createQuery("from CartItem where cartId= :cartId and productId= :productId ");
        query.setParameter("cartId", cartId)
                .setParameter("productId", productId);

        return query.uniqueResultOptional();
    }

    public void addCartItemToCart(Cart cart, Product product) {

        Optional<CartItem> cartItemOptional = findCartItemByCartAndProductId(cart.getId(), product.getId());
        if (cartItemOptional.isPresent()) {
            CartItem cartItem = cartItemOptional.get();
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        } else {
            CartItem cartItem = new CartItem(cart, product, 1);
            cart.addCartItem(cartItem);
        }
        Transaction transaction = currentSession().getTransaction();
        if (!transaction.isActive()) {
            transaction.begin();
        }
        try {
            save(cart);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }


    }
}
