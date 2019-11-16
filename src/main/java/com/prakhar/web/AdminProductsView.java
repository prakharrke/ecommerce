package com.prakhar.web;

import com.prakhar.model.Product;

import java.util.List;

public class AdminProductsView extends AbstractView {
    List<Product> products;
    AdminProductsView view;
    public AdminProductsView(String templateName, List<Product> products) {
        super(templateName);
        this.products = products;
        view = this;
    }

    public List<Product> getProducts() {
        return products;
    }

    public AdminProductsView getView() {
        return view;
    }
}
