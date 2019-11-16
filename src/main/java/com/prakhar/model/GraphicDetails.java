package com.prakhar.model;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "graphicdetails")
public class GraphicDetails extends com.prakhar.model.Entity {

    @Column
    private String brand;

    @Column
    private String model;

    @Column
    private String type;

    @Column
    private String memory;

    @OneToOne
    @JoinColumn(name = "productId")
    Product product;

    public GraphicDetails(String brand, String model, String type, String memory, Product product) {
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.memory = memory;
        this.product = product;
    }

    public GraphicDetails() {
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }

    public String getMemory() {
        return memory;
    }

    public Product getProduct() {
        return product;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String toString() {
        return String.format("%s %s Processor", this.getBrand(), this.getModel());
    }
}
