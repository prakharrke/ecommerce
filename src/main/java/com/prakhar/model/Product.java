package com.prakhar.model;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "product")
public class Product extends Entity {

    @Column
    private double price;

    @Column
    private int quantity;

    @Column
    private String modelSeries;

    @Column
    private String modelNumber;

    @Column
    private String manufacturer;

    @Column
    @Enumerated
    private TypeProduct productType;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    private OperatingSystem operatingSystem;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    private GraphicDetails graphicDetails;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    private InternalMemory internalMemory;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    private ScreenSpecifications screenSpecifications;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    private ProcessorDetails processorDetails;


    public Product(double price, int quantity, String modelSeries, String modelNumber, String manufacturer, TypeProduct productType) {

        this.price = price;
        this.quantity = quantity;
        this.modelSeries = modelSeries;
        this.modelNumber = modelNumber;
        this.manufacturer = manufacturer;
        this.productType = productType;
    }

    public Product() {
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getModelSeries() {
        return modelSeries;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public TypeProduct getProductType() {
        return productType;
    }

    public OperatingSystem getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(OperatingSystem operatingSystem) {
        this.operatingSystem = operatingSystem;
    }


    public void setGraphicDetails(GraphicDetails graphicDetails) {
        this.graphicDetails = graphicDetails;
    }

    public void setInternalMemory(InternalMemory internalMemory) {
        this.internalMemory = internalMemory;
    }

    public void setScreenSpecifications(ScreenSpecifications screenSpecifications) {
        this.screenSpecifications = screenSpecifications;
    }

    public GraphicDetails getGraphicDetails() {
        return graphicDetails;
    }

    public InternalMemory getInternalMemory() {
        return internalMemory;
    }

    public ScreenSpecifications getScreenSpecifications() {
        return screenSpecifications;
    }

    public void setProcessorDetails(ProcessorDetails processorDetails) {
        this.processorDetails = processorDetails;
    }

    public ProcessorDetails getProcessorDetails() {
        return processorDetails;
    }

    public String toString(){
        return String.format("%s %s (%s) %s %s %s Refurbished %s (%s RAM)", this.getManufacturer(), this.getModelSeries(),
                this.getModelNumber(), this.getProcessorDetails().getName(), this.getProcessorDetails().getGeneration(),
                this.getOperatingSystem().getVersion(), this.getProductType(), this.getInternalMemory().getRam());

    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setModelSeries(String modelSeries) {
        this.modelSeries = modelSeries;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setProductType(TypeProduct productType) {
        this.productType = productType;
    }
}
