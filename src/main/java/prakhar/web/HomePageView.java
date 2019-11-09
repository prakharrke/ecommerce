package prakhar.web;

import prakhar.model.Person;

public class HomePageView extends AbstractView {

    private HomePageView view;
    private Person person;
    public HomePageView(String templateName, Person person) {
        super(templateName);
        this.view = this;
        this.person = person;
    }

    public HomePageView getView() {
        return view;
    }

    public Person getPerson() {
        return person;
    }
}
