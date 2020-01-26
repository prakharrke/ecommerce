package com.prakhar.resources;

import com.prakhar.auth.User;
import com.prakhar.model.Cart;
import com.prakhar.model.Person;
import com.prakhar.model.Product;
import com.prakhar.repo.CartRepo;
import com.prakhar.repo.ProductRepo;
import com.prakhar.service.CartService;
import com.prakhar.service.PersonService;
import com.prakhar.system.PersonInjector;
import com.prakhar.system.SessionManager;
import com.prakhar.web.CartView;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;


public class OrderResource {

    private SessionManager sessionManager;
    private CartRepo cartRepo;
    private PersonService personService;
    private ProductRepo productRepo;
    private CartService cartService;

    public OrderResource(SessionManager sessionManager, CartRepo cartRepo, PersonService personService, ProductRepo productRepo, CartService cartService) {
        this.sessionManager = sessionManager;
        this.cartRepo = cartRepo;
        this.personService = personService;
        this.productRepo = productRepo;
        this.cartService = cartService;
    }




}
