package com.prakhar.web;

import com.prakhar.model.Cart;

public class CartView extends AbstractView{

    private Cart cart;
    private CartView view;

    public CartView(String templateName, Cart cart) {
        super(templateName);
        this.cart = cart;
        view = this;
    }

    public Cart getCart() {
        return cart;
    }

    public CartView getView() {
        return view;
    }
}
