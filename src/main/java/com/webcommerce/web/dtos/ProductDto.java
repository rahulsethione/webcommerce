package com.webcommerce.web.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.webcommerce.web.entities.Product;
import com.webcommerce.web.entities.ProductVariant;
import org.springframework.data.annotation.Immutable;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.List;

@Immutable
public class ProductDto implements Serializable {

    private Product product;
    private List<ProductVariant> variants;

    @JsonCreator
    public ProductDto(
            @JsonProperty Product product,
            @Nullable @JsonProperty List<ProductVariant> variants) {
        this.product = product;
        this.variants = variants;
    }

    public Product getProduct() {
        return product;
    }

    public List<ProductVariant> getVariants() {
        return variants;
    }

}
