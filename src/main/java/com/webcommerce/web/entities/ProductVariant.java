package com.webcommerce.web.entities;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

@Document(collection = "productvariants")
public class ProductVariant implements Persistable<String>, Serializable {

    @Id
    private String id;

    @NotNull
    @Field(name = "product")
    @DBRef
    private Product product;

    @NotNull
    @Field(name = "price")
    private Double price;

    @Field(name = "discount")
    private Float discount;

    @Field(name = "attributes")
    private Map<String, Object> attributes;

    @Field(name = "items")
    @DBRef
    private Collection<Item> items;

    @CreatedDate
    @Field(name = "dateCreated")
    private Date dateCreated;

    @LastModifiedDate
    @Field(name = "dateLastModified")
    private Date dateLastModified;

    public ProductVariant() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Collection<Item> getItems() {
        return items;
    }

    public void setItems(Collection<Item> items) {
        this.items = items;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateLastModified() {
        return dateLastModified;
    }

    public void setDateLastModified(Date dateLastModified) {
        this.dateLastModified = dateLastModified;
    }

    @JsonGetter("discountedPrice")
    public Double getDiscountedPrice() {
        return price - (price * discount / 100);
    }

    @Override
    @JsonIgnore
    public boolean isNew() {
        return id == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductVariant that = (ProductVariant) o;
        return price.equals(that.price) &&
                discount.equals(that.discount) &&
                Objects.equals(attributes, that.attributes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, discount, attributes);
    }

    @Override
    public String toString() {
        return "ProductVariant{" +
                "id='" + id + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", attributes=" + attributes +
                '}';
    }
}
