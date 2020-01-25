package com.prakhar.model;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@javax.persistence.Entity
@Table(name = "cart")
public class Cart extends Entity {

    @OneToOne
    @JoinColumn(name = "personId")
    private Person person;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "cart")
    Set<CartItem> cartItems;


    public Cart() {
    }

    public Cart(Person person) {
        this.person = person;
        cartItems = new LinkedHashSet<>();
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void addCartItem(CartItem cartItem) {
        cartItems.add(cartItem);
    }
}
