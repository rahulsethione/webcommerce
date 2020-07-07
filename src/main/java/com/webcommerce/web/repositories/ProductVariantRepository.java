package com.webcommerce.web.repositories;

import com.webcommerce.web.entities.ProductVariant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductVariantRepository extends MongoRepository<ProductVariant, String> { }
