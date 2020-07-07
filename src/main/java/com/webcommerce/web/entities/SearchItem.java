package com.webcommerce.web.entities;

import com.webcommerce.web.enums.ProductType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@Document(indexName = "search")
public class SearchItem implements Serializable {
    @Id
    @Field(name = "id", type = FieldType.Text)
    private String id;

    @NotNull
    @Field(name = "productId", type = FieldType.Text)
    private String productId;

    @NotNull
    @Field(name = "manufacturer", type = FieldType.Text)
    private String manufacturer;

    @NotNull
    @Field(name = "productName", type = FieldType.Text)
    private String productName;

    @Field(name = "productImages", type = FieldType.Keyword)
    private String productImages;

    @NotNull
    @Field(name = "categoryId", type = FieldType.Text)
    private String categoryId;

    @NotNull
    @Field(name = "categoryName", type = FieldType.Text)
    private String categoryName;

    @NotNull
    @Field(name = "productType", type = FieldType.Text)
    private ProductType productType;

    @NotNull
    @Field(name = "productVariantId", type = FieldType.Text)
    private String productVariantId;

    @NotNull
    @Field(name = "price", type = FieldType.Double)
    private Double price;

    @Field(name = "discount", type = FieldType.Float)
    private Float discount;

    @Field(name = "attributes" , type = FieldType.Object)
    private Map<String, Object> attributes;

    @NotNull
    @Field(name = "itemId", type = FieldType.Text)
    private String itemId;

    @NotNull
    @Field(name = "productCode", type = FieldType.Text)
    private String productCode;

    @NotNull
    @Field(name = "seller", type = FieldType.Text)
    private String seller;

    @CreatedDate
    @Field(name = "dateCreated", type = FieldType.Date)
    private Date dateCreated;

    @LastModifiedDate
    @Field(name = "dateLastModified", type = FieldType.Date)
    private Date dateLastModified;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImages() {
        return productImages;
    }

    public void setProductImages(String productImages) {
        this.productImages = productImages;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public String getProductVariantId() {
        return productVariantId;
    }

    public void setProductVariantId(String productVariantId) {
        this.productVariantId = productVariantId;
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

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
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
}
