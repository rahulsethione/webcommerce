package com.webcommerce.web.dao;

import com.webcommerce.web.dtos.ProductDto;
import com.webcommerce.web.entities.Product;
import com.webcommerce.web.entities.ProductVariant;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface ProductDaoService {
    @Transactional List<ProductVariant> addProduct(ProductDto product);
    List<Product> getProducts();
    List<ProductVariant> getProductVariantsLastUpdated(Date dateAfterUpdate);
}
