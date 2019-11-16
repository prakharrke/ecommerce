package com.prakhar.model;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "processordetails")
public class ProcessorDetails extends com.prakhar.model.Entity {

    @Column
    private String brand;

    @Column
    private String name;

    @Column
    private String generation;

    @Column
    private String variant;

    @Column
    private int numberOfCores;

    @Column
    private String processorSpeed;

    @Column
    private String cache;

    @OneToOne
    @JoinColumn(name = "productId")
    Product product;

    public ProcessorDetails(String brand, String name, String generation, String variant, int numberOfCores, String processorSpeed, String cache, Product product) {
        this.brand = brand;
        this.name = name;
        this.generation = generation;
        this.variant = variant;
        this.numberOfCores = numberOfCores;
        this.processorSpeed = processorSpeed;
        this.cache = cache;
        this.product = product;
    }

    public ProcessorDetails() {
    }

    public String getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public String getGeneration() {
        return generation;
    }

    public String getVariant() {
        return variant;
    }

    public int getNumberOfCores() {
        return numberOfCores;
    }

    public String getProcessorSpeed() {
        return processorSpeed;
    }

    public String getCache() {
        return cache;
    }

    public Product getProduct() {
        return product;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public void setNumberOfCores(int numberOfCores) {
        this.numberOfCores = numberOfCores;
    }

    public void setProcessorSpeed(String processorSpeed) {
        this.processorSpeed = processorSpeed;
    }

    public void setCache(String cache) {
        this.cache = cache;
    }

    public String toString() {
        return String.format("%s %s %s %s having speed of %s", this.getBrand(), this.getName(), this.getGeneration(), this.getVariant(), this.getProcessorSpeed());
    }
}
