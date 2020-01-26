package com.prakhar.resources;


import com.prakhar.model.Cart;
import com.prakhar.model.CartItem;
import com.prakhar.model.Product;
import com.prakhar.repo.CartRepo;
import com.prakhar.repo.ProductRepo;
import com.prakhar.service.CartService;
import com.prakhar.service.PersonService;
import com.prakhar.system.PersonInjector;
import com.prakhar.web.BillingDetailsView;
import com.prakhar.web.CartView;
import com.prakhar.web.ProductDetailView;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import com.prakhar.auth.User;
import com.prakhar.model.Person;
import com.prakhar.repo.PersonRepo;
import com.prakhar.utils.WebUtils;
import com.prakhar.web.HomePageView;
import org.apache.http.client.utils.URIBuilder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Optional;

@Path("/")
public class HomeResource {

    private PersonRepo personRepo;
    private ProductRepo productRepo;
    private PersonService personService;
    private CartService cartService;
    private CartRepo cartRepo;

    public HomeResource(PersonRepo personRepo, ProductRepo productRepo, PersonService personService, CartService cartService, CartRepo cartRepo) {
        this.personRepo = personRepo;
        this.productRepo = productRepo;
        this.personService = personService;
        this.cartService = cartService;
        this.cartRepo = cartRepo;
    }

    @Path("home")
    @GET
    @UnitOfWork
    public HomePageView getHomePage(@Auth Optional<User> userOptional) {
        Person person = null;
        if (userOptional.isPresent()) {
            String personEmail = userOptional.get().getEmail();
            Optional<Person> personOptional = personRepo.findPersonByEmail(personEmail);
            if (!personOptional.isPresent()) {
                throw new WebApplicationException(
                        Response.status(302).location(
                                URI.create("/app/accountServices/login")
                        ).cookie(
                                WebUtils.createNewCookie("token", "")
                        ).build()
                );
            }
            person = personOptional.get();
        }

        return new HomePageView("home.ftl", person, productRepo);
    }

    @Path("/billing")
    @GET
    @UnitOfWork
    public BillingDetailsView getProileView(@Auth User user) {
        String personEmail = user.getEmail();
        Optional<Person> personOptional = personRepo.findPersonByEmail(personEmail);
        if (!personOptional.isPresent()) {
            throw new WebApplicationException(
                    Response.status(302).location(
                            URI.create("/app/accountServices/login")
                    ).cookie(
                            WebUtils.createNewCookie("token", "")
                    ).build()
            );
        }

        Person person = personOptional.get();
        return new BillingDetailsView("billingDetails.ftl", person);
    }

    @Path("/product/{productId}")
    @GET
    @UnitOfWork
    public ProductDetailView getProductDetails(@Auth Optional<User> userOptional, @PathParam("productId") Long productId) {
        Optional<Product> productOptional = productRepo.findProductById(productId);
        if (!productOptional.isPresent()) {
            throw new WebApplicationException(
                    Response.status(302).location(
                            URI.create("/app/home")
                    ).build()
            );
        }
        Product product = productOptional.get();
        return new ProductDetailView("productDetail.ftl", product);
    }

    @GET
    @Path("/cart")
    @UnitOfWork
    public CartView getCart(@Auth User user, @PersonInjector Person person) {
        Cart cart = person.getCart();
        if (cart == null) {
            cart = personService.createCartForPerson(person);
        }

        return new CartView("cart.ftl", cart);
    }

    @GET
    @Path("/cart/addToCart/{productId}")
    @UnitOfWork
    public Response addProductToCart(@Auth User user,
                                     @PersonInjector Person person,
                                     @PathParam("productId") Long productId) {

        Product product = productRepo.findProductById(productId).orElseThrow(() -> new RuntimeException("Could not find product by given productId"));
        Cart cart = person.getCart();
        if (cart == null) {
            cart = personService.createCartForPerson(person);
            CartItem cartItem = new CartItem(cart, product, 1);
            cart.addCartItem(cartItem);
            cartRepo.save(cart);
        } else {
            cartService.addCartItemToCart(cart, product);
        }
        return Response
                .status(302)
                .location(
                        UriBuilder.fromPath("/app/cart").build()
                )
                .build();
    }

    @GET
    @Path("/cart/increaseCartItem/{cartItemId}")
    @UnitOfWork
    public Response increaseCartItemCount(@Auth User user, @PersonInjector Person person, @PathParam("cartItemId") Long cartItemId) {
        Cart cart = person.getCart();
        if (cart == null) {
            throw new RuntimeException("Cart is not present for the given user");
        }

        CartItem cartItem = cart.getCartItems()
                .stream()
                .filter(item -> item.getId().equals(cartItemId))
                .findFirst().orElseThrow(() -> new RuntimeException("Given cartItemId not found in current user's cart"));
        cartItem.increaseQuantity();
        cartRepo.save(cart);

        return Response.status(302)
                .location(
                        UriBuilder.fromPath("/app/cart/").build()
                ).build();
    }

    @GET
    @Path("/cart/decreaseCartItem/{cartItemId}")
    @UnitOfWork
    public Response decreaseCartItemCount(@Auth User user, @PersonInjector Person person, @PathParam("cartItemId") Long cartItemId) {
        Cart cart = person.getCart();
        if (cart == null) {
            throw new RuntimeException("Cart is not present for the given user");
        }

        CartItem cartItem = cart.getCartItems()
                .stream()
                .filter(item -> item.getId().equals(cartItemId))
                .findFirst().orElseThrow(() -> new RuntimeException("Given cartItemId not found in current user's cart"));
        cartItem.decreaseQuantity();
        cartRepo.save(cart);

        return Response.status(302)
                .location(
                        UriBuilder.fromPath("/app/cart/").build()
                ).build();
    }

    @GET
    @Path("/cart/removeCartItem/{cartItemId}")
    @UnitOfWork
    public Response removeCartItem(@Auth User user, @PersonInjector Person person, @PathParam("cartItemId") Long cartItemId) {
        Cart cart = person.getCart();
        if (cart == null) {
            throw new RuntimeException("Cart is not present for the given user");
        }

        CartItem cartItem = cart.getCartItems()
                .stream()
                .filter(item -> item.getId().equals(cartItemId))
                .findFirst().orElseThrow(() -> new RuntimeException("Given cartItemId not found in current user's cart"));
        cart.getCartItems().remove(cartItem);
        cartRepo.delete(cartItem);

        return Response.status(302)
                .location(
                        UriBuilder.fromPath("/app/cart/").build()
                ).build();
    }
}
