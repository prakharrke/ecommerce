package com.prakhar.web;

import com.prakhar.model.Person;
import com.prakhar.model.Product;
import com.prakhar.repo.ProductRepo;

import java.util.List;

public class HomePageView extends AbstractView {

    private HomePageView view;
    private Person person;
    ProductRepo productRepo;
    public HomePageView(String templateName, Person person, ProductRepo productRepo) {
        super(templateName);
        this.view = this;
        this.person = person;
        this.productRepo = productRepo;
    }

    public HomePageView getView() {
        return view;
    }

    public Person getPerson() {
        return person;
    }

    public List<Product> getProducts() {
        return productRepo.getProducts();
    }
}
