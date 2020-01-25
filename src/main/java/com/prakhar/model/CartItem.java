package com.prakhar.model;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "cartitem")
public class CartItem  extends com.prakhar.model.Entity {

    @ManyToOne
    @JoinColumn(name = "cartId")
    Cart cart;

    @OneToOne
    @JoinColumn(name = "productId")
    Product product;

    @Column
    int quantity;

    public CartItem() {
    }

    public CartItem(Cart cart, Product product, int quantity) {
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
    }

    public Cart getCart() {
        return cart;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
