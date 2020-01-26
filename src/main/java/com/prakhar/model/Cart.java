package com.prakhar.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@javax.persistence.Entity
@Table(name = "cart")
public class Cart extends Entity {

    @OneToOne
    @JoinColumn(name = "personId")
    private Person person;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "cart")
    List<CartItem> cartItems = new ArrayList<>();


    public Cart() {
    }

    public Cart(Person person) {
        this.person = person;
        cartItems = new ArrayList<>();
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void addCartItem(CartItem cartItem) {
        cartItems.add(cartItem);
    }
}
