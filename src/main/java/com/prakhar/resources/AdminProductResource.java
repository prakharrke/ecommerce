package com.prakhar.resources;

import com.prakhar.auth.User;
import com.prakhar.model.Product;
import com.prakhar.repo.ProductRepo;
import com.prakhar.web.AdminCreateProductsView;
import com.prakhar.web.AdminProductDetailsView;
import com.prakhar.web.AdminProductsView;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Path("/admin")
public class AdminProductResource {

    ProductRepo productRepo;

    public AdminProductResource(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @GET
    @Path("/")
    @UnitOfWork
    public AdminProductsView getAdminProducts(@Auth User user) {
        List<Product> products = productRepo.getProducts();
        return new AdminProductsView("adminProducts.ftl", products);
    }

    @GET
    @Path("/product/{productId}")
    @UnitOfWork
    public AdminProductDetailsView getAdminProductDetails(@PathParam("productId") Long productId, @QueryParam("message") String message) {
        Optional<Product> productOptional = productRepo.findProductById(productId);
        if (!productOptional.isPresent()) {
            throw new WebApplicationException(
                    Response.status(302).location(
                            URI.create("/app/admin")
                    ).build()
            );
        }


        return new AdminProductDetailsView("adminProductDetails.ftl", productOptional.get(), message);
    }


    @POST
    @Path("/createProduct")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @UnitOfWork(transactional = false)
    public Response createProduct(@Auth User user,
                                  @FormParam("price") Double price,
                                  @FormParam("quantity") int quantity,
                                  @FormParam("modelSeries") String modelSeries,
                                  @FormParam("modelNumber") String modelNumber,
                                  @FormParam("manufacturer") String manufacturer,
                                  @FormParam("productType") String productType,
                                  @FormParam("osType") String osType,
                                  @FormParam("osVersion") String osVersion,
                                  @FormParam("graphicBrand") String graphicBrand,
                                  @FormParam("graphicType") String graphicType,
                                  @FormParam("graphicModel") String graphicModel,
                                  @FormParam("graphicMemory") String graphicMemory,
                                  @FormParam("internalMemoryType") String internalMemoryType,
                                  @FormParam("internalMemoryRam") String internalMemoryRam,
                                  @FormParam("screenSize") String screenSize,
                                  @FormParam("screenType") String screenType,
                                  @FormParam("screenRatio") String screenRatio,
                                  @FormParam("screenResolution") String screenResolution,
                                  @FormParam("processorBrand") String processorBrand,
                                  @FormParam("processorName") String processorName,
                                  @FormParam("processorGeneration") String processorGeneration,
                                  @FormParam("processorVariant") String processorVariant,
                                  @FormParam("processorNumberOfCores") int processorNumberOfCores,
                                  @FormParam("processorspeed") String processorSpeed,
                                  @FormParam("processorCache") String processorCache) {
        try {

            productRepo.createProduct(price, quantity, modelSeries, modelNumber, manufacturer, productType, osType,
                    osVersion, graphicBrand, graphicType, graphicModel, graphicMemory, internalMemoryType, internalMemoryRam,
                    screenSize, screenRatio, screenResolution, screenType, processorBrand, processorName, processorGeneration,
                    processorVariant, processorNumberOfCores, processorSpeed, processorCache);
            return Response.status(302).location(
                    UriBuilder.fromPath("/app/admin/create-product").queryParam("message", "success").build()
            ).build();
        } catch (Exception e) {
            throw new WebApplicationException(
                    Response.status(302).location(
                            UriBuilder.fromPath("/app/admin/create-product").queryParam("message", "error").build()
                    ).build()
            );
        }

    }

    @POST
    @Path("/updateProduct/{productId}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @UnitOfWork
    public Response updateProduct(@Auth User user,
                                  @PathParam("productId") Long productId,
                                  @FormParam("price") Double price,
                                  @FormParam("quantity") int quantity,
                                  @FormParam("modelSeries") String modelSeries,
                                  @FormParam("modelNumber") String modelNumber,
                                  @FormParam("manufacturer") String manufacturer,
                                  @FormParam("productType") String productType,
                                  @FormParam("osType") String osType,
                                  @FormParam("osVersion") String osVersion,
                                  @FormParam("graphicBrand") String graphicBrand,
                                  @FormParam("graphicType") String graphicType,
                                  @FormParam("graphicModel") String graphicModel,
                                  @FormParam("graphicMemory") String graphicMemory,
                                  @FormParam("internalMemoryType") String internalMemoryType,
                                  @FormParam("internalMemoryRam") String internalMemoryRam,
                                  @FormParam("screenSize") String screenSize,
                                  @FormParam("screenType") String screenType,
                                  @FormParam("screenRatio") String screenRatio,
                                  @FormParam("screenResolution") String screenResolution,
                                  @FormParam("processorBrand") String processorBrand,
                                  @FormParam("processorName") String processorName,
                                  @FormParam("processorGeneration") String processorGeneration,
                                  @FormParam("processorVariant") String processorVariant,
                                  @FormParam("processorNumberOfCores") int processorNumberOfCores,
                                  @FormParam("processorspeed") String processorSpeed,
                                  @FormParam("processorCache") String processorCache) {
        Optional<Product> productOptional = productRepo.findProductById(productId);
        if (!productOptional.isPresent()) {
            throw new WebApplicationException(
                    Response.status(302).location(
                            URI.create("/app/admin")
                    ).build()
            );
        }
        try {
            productRepo.updateProduct(productOptional.get(), price, quantity, modelSeries, modelNumber, manufacturer,
                    productType, osType, osVersion, graphicBrand, graphicType, graphicModel, graphicMemory,
                    internalMemoryType, internalMemoryRam, screenSize, screenRatio, screenResolution, screenType,
                    processorBrand, processorName, processorGeneration, processorVariant, processorNumberOfCores,
                    processorSpeed, processorCache);

            return Response.status(302).location(
                    UriBuilder.fromPath("/app/admin/product/" + productId).queryParam("message", "success").build()
            ).build();
        } catch (Exception e) {
            return Response.status(302).location(
                    UriBuilder.fromPath("/app/admin/product/" + productId).queryParam("message", "error").build()
            ).build();
        }

    }

    @Path("/create-product")
    @GET
    public AdminCreateProductsView createProductsView(@Auth User user, @QueryParam("message") String message) {
        return new AdminCreateProductsView("createProduct.ftl", message);
    }

    @Path("/deleteProduct/{productId}")
    @POST
    @UnitOfWork
    public Response deleteProduct(@Auth User user, @PathParam("productId") Long productId) {
        Optional<Product> productOptional = productRepo.findProductById(productId);
        if (!productOptional.isPresent()) {
            throw new WebApplicationException(
                    Response.status(302).location(
                            URI.create("/app/admin")
                    ).build()
            );
        }
        try{
            productRepo.deleteProduct(productOptional.get());
            return  Response.status(302).location(
                    URI.create("/app/admin")
            ).build();
        }catch (Exception e){
            throw new WebApplicationException(
                    Response.status(302).location(
                            UriBuilder.fromPath("/app/admin/product/" + productId).queryParam("message", "error").build()
                    ).build()
            );
        }
    }
}











