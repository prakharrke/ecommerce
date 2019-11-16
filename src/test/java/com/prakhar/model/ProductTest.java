package com.prakhar.model;

import com.prakhar.repo.ProductRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.when;

public class ProductTest {
    private ProductRepo productRepo;
    private Product laptop;

    @Before
    public void setUp() {
        productRepo = Mockito.mock(ProductRepo.class);
        laptop = new Product(10000, 20, "ACACACAC", "111111", "HP", TypeProduct.LAPTOP);
    }

    @Test
    public void getProductByModelNumberAndSeries() {
        Optional<Product> productOptional = Optional.ofNullable(laptop);
        when(productRepo.getProductByModelNumberAndSeries("111111","ACACAC")).thenReturn(productOptional);
        Assert.assertEquals(productOptional.get(), laptop);
    }

    @Test
    public void getProductDetailsTest() {

        ScreenSpecifications screenSpecifications = new ScreenSpecifications("15\"", "LED", "2048", "16:9", laptop);
        GraphicDetails graphicDetails = new GraphicDetails("Intel", "xxxxxxxxx", "Integrated", "2048GB", laptop);
        InternalMemory internalMemory = new InternalMemory("6 GB", "DDR3", laptop);
        ProcessorDetails processorDetails = new ProcessorDetails("Intel", "latest", "3rd", "42000U", 4, 2.25, 4, laptop);
        OperatingSystem operatingSystem = new OperatingSystem("Windows", "Windows 8", laptop);
        laptop.setScreenSpecifications(screenSpecifications);
        laptop.setGraphicDetails(graphicDetails);
        laptop.setInternalMemory(internalMemory);
        laptop.setOperatingSystem(operatingSystem);
        laptop.setProcessorDetails(processorDetails);

        Optional<Product> productOptional = Optional.ofNullable(laptop);
        when(productRepo.getProductByModelNumberAndSeries("111111","ACACAC")).thenReturn(productOptional);

        Assert.assertEquals(productOptional.get().getGraphicDetails(), graphicDetails);
        Assert.assertEquals(productOptional.get().getInternalMemory(), internalMemory);
        Assert.assertEquals(productOptional.get().getOperatingSystem(), operatingSystem);
        Assert.assertEquals(productOptional.get().getProcessorDetails(), processorDetails);
        Assert.assertEquals(productOptional.get().getScreenSpecifications(), screenSpecifications);
    }
}
