package com.prakhar.web;

import com.prakhar.model.BillingAddress;
import com.prakhar.model.Cart;
import com.prakhar.model.Person;

public class CheckoutView extends AbstractView {
    CheckoutView view;
    Cart cart;
    Person person;


    public CheckoutView(String templateName, Cart cart, Person person) {
        super(templateName);
        this.cart = cart;
        this.person = person;
        view = this;
    }

    public CheckoutView getView() {
        return view;
    }

    public Cart getCart() {
        return cart;
    }

    public Person getPerson() {
        return person;
    }
}
