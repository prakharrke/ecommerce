package com.prakhar.web;

import com.prakhar.model.Person;

public class BillingDetailsView extends AbstractView{


    private Person person;
    private BillingDetailsView view;

    public BillingDetailsView(String templateName, Person person) {
        super(templateName);
        this.person = person;
        view = this;
    }

    public Person getPerson() {
        return person;
    }

    public BillingDetailsView getView() {
        return view;
    }
}
