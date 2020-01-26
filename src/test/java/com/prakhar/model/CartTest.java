package com.prakhar.model;

import com.prakhar.repo.CartRepo;
import com.prakhar.repo.PersonRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CartTest {

    private PersonRepo personRepo;
    private Person person;
    private Product laptop;
    private CartRepo cartRepo;

    @Before
    public void setUp() {
        personRepo = mock(PersonRepo.class);
        cartRepo = mock(CartRepo.class);
        person = mock(Person.class);
        laptop = new Product(10000, "ACACACAC", "111111", "HP", TypeProduct.LAPTOP);
    }

    @Test
    public void getCart() {
        Cart cart = new Cart(person);
        when(personRepo.getCartFromPersonId(person)).thenReturn(cart);
        Cart cart1 = personRepo.getCartFromPersonId(person);
        Assert.assertEquals(cart, cart1);
    }

    @Test
    public void addCartItemToCart() {
        ScreenSpecifications screenSpecifications = new ScreenSpecifications("15\"", "LED", "2048", "16:9", laptop);
        GraphicDetails graphicDetails = new GraphicDetails("Intel", "xxxxxxxxx", "Integrated", "2048GB", laptop);
        InternalMemory internalMemory = new InternalMemory("6 GB", "DDR3", laptop);
        ProcessorDetails processorDetails = new ProcessorDetails("Intel", "latest", "3rd", "42000U", 4, "2.25", "4", laptop);
        OperatingSystem operatingSystem = new OperatingSystem("Windows", "Windows 8", laptop);
        laptop.setScreenSpecifications(screenSpecifications);
        laptop.setGraphicDetails(graphicDetails);
        laptop.setInternalMemory(internalMemory);
        laptop.setOperatingSystem(operatingSystem);
        laptop.setProcessorDetails(processorDetails);

        Cart cart = new Cart(person);
        when(personRepo.getCartFromPersonId(person)).thenReturn(cart);
        Cart cart1 = personRepo.getCartFromPersonId(person);
        //cartRepo.addCartItemToCart(cart, laptop);

        Assert.assertEquals(cart.getCartItems().size(), 1);
    }

}
