package com.webcommerce.web.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webcommerce.web.enums.ProductType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Document(collection = "products")
public class Product implements Persistable<String>, Serializable {

    @Id
    private String id;

    @NotNull
    @Field(name = "manufacturer")
    private String manufacturer;

    @NotNull
    @Field(name = "name")
    @Indexed
    private String name;

    @Field(name = "images")
    private Set<String> images;

    @NotNull
    @Field(name = "category")
    @DBRef
    private Category category;

    @NotNull
    @Field(name = "productType")
    private ProductType productType;

    @CreatedDate
    @Field(name = "dateCreated")
    private Date dateCreated;

    @LastModifiedDate
    @Field(name = "dateLastModified")
    private Date dateLastModified;

    public Product() { }

    @Override
    public String getId() {
        return id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getImages() {
        return images;
    }

    public void setImages(Set<String> images) {
        this.images = images;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getDateLastModified() {
        return dateLastModified;
    }

    @Override
    @JsonIgnore
    public boolean isNew() {
        return StringUtils.isEmpty(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return manufacturer.equals(product.manufacturer) &&
                name.equals(product.name) &&
                category.equals(product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manufacturer, name, category);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", name='" + name + '\'' +
                ", images=" + images +
                ", category=" + category +
                ", dateCreated=" + dateCreated +
                ", dateLastModified=" + dateLastModified +
                '}';
    }
}
