package com.prakhar.model;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name="internalmemory")
public class InternalMemory extends com.prakhar.model.Entity {

    @Column
    private String ram;

    @Column
    private String type;

    @OneToOne
    @JoinColumn(name="productId")
    private Product product;

    public InternalMemory() {
    }

    public InternalMemory(String ram, String type, Product product) {
        this.ram = ram;
        this.type = type;
        this.product = product;
    }

    public String getRam() {
        return ram;
    }

    public String getType() {
        return type;
    }

    public Product getProduct() {
        return product;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public void setType(String type) {
        this.type = type;
    }
}
