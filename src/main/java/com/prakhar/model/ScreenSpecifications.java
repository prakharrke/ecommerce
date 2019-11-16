package com.prakhar.model;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name = "screenspecifications")
public class ScreenSpecifications extends com.prakhar.model.Entity {

    @Column
    private String size;

    @Column
    private String type;

    @Column
    private String resolution;

    @Column
    private String ratio;

    @OneToOne
    @JoinColumn(name = "productId")
    Product product;

    public ScreenSpecifications() {
    }

    public ScreenSpecifications(String size, String type, String resolution, String ratio, Product product) {
        this.size = size;
        this.type = type;
        this.resolution = resolution;
        this.ratio = ratio;
        this.product = product;
    }

    public String getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    public String getResolution() {
        return resolution;
    }

    public String getRatio() {
        return ratio;
    }

    public Product getProduct() {
        return product;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public String toString() {
        return String.format("%s %s Display Screen", this.getSize(), this.getType());
    }
}
