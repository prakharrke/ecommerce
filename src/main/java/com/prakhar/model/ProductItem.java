package com.prakhar.model;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "productItem")
public class ProductItem extends Entity {

    @ManyToOne
    @JoinColumn(name = "productid")
    private Product product;

    @Column
    private String serialNumber;

    public ProductItem() {
    }

    public ProductItem(Product product) {
        this.product = product;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Product getProduct() {
        return product;
    }

    public String getSerialNumber() {
        return serialNumber;
    }
}
