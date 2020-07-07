package com.webcommerce.web.dao;

import com.webcommerce.web.dtos.ProductDto;
import com.webcommerce.web.entities.Product;
import com.webcommerce.web.entities.ProductVariant;
import com.webcommerce.web.repositories.ProductRepository;
import com.webcommerce.web.repositories.ProductVariantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductDaoServiceImpl implements ProductDaoService {

    private static Logger logger = LoggerFactory.getLogger(ProductDaoServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductVariantRepository productVariantRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    public void postConstruct() {
        logger.info("Syncronizing collection: 'products', 'productvariants'.");
        if(!mongoTemplate.collectionExists(Product.class)) {
            mongoTemplate.createCollection(Product.class);
        }

        if(!mongoTemplate.collectionExists(ProductVariant.class)) {
            mongoTemplate.createCollection(ProductVariant.class);
        }
    }

    @Override
    @Transactional
    public List<ProductVariant> addProduct(ProductDto productDto) {
        Product newProduct = productRepository.save(productDto.getProduct());

        for(ProductVariant variant: productDto.getVariants()) {
            variant.setProduct(newProduct);
        }

        List<ProductVariant> variants = productVariantRepository.saveAll(productDto.getVariants());

        Iterable iterable = productVariantRepository.findAllById(variants.stream()
                .map(ProductVariant::getId)
                .collect(Collectors.toList())
        );

        List<ProductVariant> results = new ArrayList<>();

        iterable.forEach(variant -> results.add((ProductVariant) variant));

        return results;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<ProductVariant> getProductVariantsLastUpdated(Date dateAfterUpdate) {
        Query query = new Query();

        Criteria criteria = Criteria.where("dateLastModified").gt(dateAfterUpdate);

        query.addCriteria(criteria);

        return mongoTemplate.find(query, ProductVariant.class);
    }
}
