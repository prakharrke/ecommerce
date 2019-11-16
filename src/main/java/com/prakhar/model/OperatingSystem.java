package com.prakhar.model;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Table(name="Operatingsystem")
public class OperatingSystem extends com.prakhar.model.Entity {

    @Column
    String type;

    @Column
    String version;

    @OneToOne
    @JoinColumn(name = "productId")
    Product product;

    public OperatingSystem() {
    }


    public OperatingSystem(String type, String version, Product product) {
        this.type = type;
        this.version = version;
        this.product = product;
    }

    public String getType() {
        return type;
    }

    public String getVersion() {
        return version;
    }

    public Product getProduct() {
        return product;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String toString() {
        return String.format("%s Operating System", this.getVersion());
    }
}
