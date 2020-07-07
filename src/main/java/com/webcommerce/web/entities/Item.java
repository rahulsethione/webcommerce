package com.webcommerce.web.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Document(collection = "items")
public class Item implements Persistable<String>, Serializable {

    public enum Status {
        AVAILABLE,
        WITHDRAWN,
        SHIPPED,
        DELIVERED,
        RETURNED
    }

    @Id
    private String id;

    @NotNull
    @Field(name = "productCode")
    private String productCode;

    @NotNull
    @Field(name = "seller")
    private String seller;

    @Field(name = "status")
    private Status status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean isNew() {
        return id == null;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", seller='" + seller + '\'' +
                ", productCode=" + productCode +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return productCode.equals(item.productCode) &&
                seller.equals(item.seller);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productCode, seller);
    }
}
