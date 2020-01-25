package com.prakhar.repo;

import com.prakhar.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;


public class ProductRepo extends AbstractRepo {

    public ProductRepo(SessionFactory sessionFactory) {
        super(sessionFactory);

    }


    public Optional<Product> getProductByModelNumberAndSeries(String modelNumber, String modelSeries) {
        Query<Product> query = currentSession().createQuery("select p from Product p" +
                " where modelNumber=:modelNumber and modelSeries=:modelSeries");
        query.setParameter("modelNumber", modelNumber);
        query.setParameter("modelSeries", modelSeries);
        return query.uniqueResultOptional();

    }

    public Optional<Product> findProductById(Long id) {
        Query query = currentSession().createQuery("from Product where id= :productId");
        query.setParameter("productId", id);
        return query.uniqueResultOptional();
    }

    public void createProduct(Double price, String modelSeries, String modelNumber, String manufacturer, String productType, String osType, String osVersion, String graphicBrand, String graphicType, String graphicModel, String graphicMemory, String internalMemoryType, String internalMemoryRam, String screenSize, String screenRatio, String screenResolution, String screenType, String processorBrand, String processorName, String processorGeneration, String processorVariant, int processorNumberOfCores, String processorSpeed, String processorCache) {

        Product product = new Product(price, modelSeries, modelNumber, manufacturer, TypeProduct.fromDisplayName(productType));

        OperatingSystem operatingSystem = new OperatingSystem(osType, osVersion, product);
        product.setOperatingSystem(operatingSystem);

        GraphicDetails graphicDetails = new GraphicDetails(graphicBrand, graphicModel, graphicType, graphicMemory, product);
        product.setGraphicDetails(graphicDetails);

        InternalMemory internalMemory = new InternalMemory(internalMemoryRam, internalMemoryType, product);
        product.setInternalMemory(internalMemory);

        ScreenSpecifications screenSpecifications = new ScreenSpecifications(screenSize, screenType, screenResolution, screenRatio, product);
        product.setScreenSpecifications(screenSpecifications);

        ProcessorDetails processorDetails = new ProcessorDetails(processorBrand, processorName, processorGeneration, processorVariant, processorNumberOfCores, processorSpeed, processorCache, product);
        product.setProcessorDetails(processorDetails);

        save(product);
        /*Transaction transaction = currentSession().getTransaction();
        if (!transaction.isActive()) {
            transaction.begin();
        }
        try {
            save(product);
            product.toString();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);

        }*/
    }

    public List<Product> getProducts() {
        Query query = currentSession().createQuery("from Product order by id desc");
        return query.getResultList();
    }

    public void updateProduct(Product product, Double price, String modelSeries, String modelNumber, String manufacturer, String productType, String osType, String osVersion, String graphicBrand, String graphicType, String graphicModel, String graphicMemory, String internalMemoryType, String internalMemoryRam, String screenSize, String screenRatio, String screenResolution, String screenType, String processorBrand, String processorName, String processorGeneration, String processorVariant, int processorNumberOfCores, String processorSpeed, String processorCache) {
        Transaction transaction = currentSession().getTransaction();
        if (!transaction.isActive()) {
            transaction.begin();
        }
        try {
            product.setPrice(price);
            product.setManufacturer(manufacturer);
            product.setModelSeries(modelSeries);

            product.getScreenSpecifications().setRatio(screenRatio);
            product.getScreenSpecifications().setResolution(screenResolution);
            product.getScreenSpecifications().setSize(screenSize);
            product.getScreenSpecifications().setType(screenType);

            product.getOperatingSystem().setType(osType);
            product.getOperatingSystem().setVersion(osVersion);

            product.getGraphicDetails().setBrand(graphicBrand);
            product.getGraphicDetails().setMemory(graphicMemory);
            product.getGraphicDetails().setModel(graphicModel);
            product.getGraphicDetails().setType(graphicType);

            product.getInternalMemory().setRam(internalMemoryRam);
            product.getInternalMemory().setType(internalMemoryType);

            product.getProcessorDetails().setBrand(processorBrand);
            product.getProcessorDetails().setCache(processorCache);
            product.getProcessorDetails().setGeneration(processorGeneration);
            product.getProcessorDetails().setName(processorName);
            product.getProcessorDetails().setNumberOfCores(processorNumberOfCores);
            product.getProcessorDetails().setProcessorSpeed(processorSpeed);
            product.getProcessorDetails().setVariant(processorVariant);

            save(product);
            transaction.commit();

        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }finally {

        }
    }

    public void deleteProduct(Product product) {
        Transaction transaction = currentSession().getTransaction();
        if(!transaction.isActive()) {
            transaction.begin();
        }
        try {
            delete(product);
            transaction.commit();
        }catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }
    }
}
