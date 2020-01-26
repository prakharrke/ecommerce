package com.prakhar.service;

import com.prakhar.model.Cart;
import com.prakhar.model.CartItem;
import com.prakhar.model.Product;
import com.prakhar.repo.CartRepo;

import java.util.Optional;

public class CartService {

    private CartRepo cartRepo;

    public CartService(CartRepo cartRepo) {
        this.cartRepo = cartRepo;
    }

    public void addCartItemToCart(Cart cart, Product product) {

        Optional<CartItem> cartItemOptional = cartRepo.findCartItemByCartAndProductId(cart.getId(), product.getId());
        if(cartItemOptional.isPresent()) {
            CartItem cartItem = cartItemOptional.get();
            // * TODO: Verify that there are enough productItems available
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        }else {
            CartItem cartItem = new CartItem(cart, product,1);
            cart.addCartItem(cartItem);
        }

        cartRepo.save(cart);

    }


}
