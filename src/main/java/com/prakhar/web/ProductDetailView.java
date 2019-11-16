package com.prakhar.web;

import com.prakhar.model.Product;

public class ProductDetailView extends AbstractView {
    private ProductDetailView view;
    private Product product;
    public ProductDetailView(String templateName, Product product) {
        super(templateName);
        view = this;
        this.product = product;
    }

    public ProductDetailView getView() {
        return view;
    }

    public Product getProduct() {
        return product;
    }
}
