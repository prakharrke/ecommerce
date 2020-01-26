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

    public void increaseQuantity() {
        quantity+=1;
    }

    public void decreaseQuantity() {
        if(quantity == 1) {
            throw new RuntimeException("Quantity already 1. Cannot reduce further. Please remove the product from the cart to reduce further.");
        }
        quantity -=1;
    }

    public Double getCartItemTotal() {
        return (product.getPrice() * quantity);
    }
}
