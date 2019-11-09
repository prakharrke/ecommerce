package com.prakhar.model;

import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.OffsetDateTime;

@MappedSuperclass
public abstract class Entity implements IdProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    protected Long id = null;

    @Type(type = "org.hibernate.type.OffsetDateTimeType")
    @Column(name = "created")
    protected OffsetDateTime created = OffsetDateTime.now();

    //Modified date is updated from EntityEventListener automatically
    @Type(type = "org.hibernate.type.OffsetDateTimeType")
    @Column(name = "modified")
    protected OffsetDateTime modified = OffsetDateTime.now();

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;

        Entity entity = (Entity) o;

        return getId() != null ? getId().equals(entity.getId()) : entity.getId() == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public OffsetDateTime getCreated() {
        return created;
    }

    public OffsetDateTime getModified() {
        return modified;
    }

    // Called from a Hibernate PreUpdateListener
    public void updateModified() {
        modified = OffsetDateTime.now();
    }

    public String getTag() {
        return getClass().getSimpleName() + "-" + getId();
    }

    public String getLockKey() {
        return getClass().getName() + "-" + getId();
    }

    @Override
    public String toString() {
        return "Entity{" +
                "class=" + getClass().getName() +
                ", id=" + id +
                '}';
    }
}