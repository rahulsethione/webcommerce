package com.webcommerce.web.controllers;

import com.webcommerce.web.dao.ItemDaoService;
import com.webcommerce.web.dao.ProductDaoService;
import com.webcommerce.web.dtos.ProductDto;
import com.webcommerce.web.entities.Item;
import com.webcommerce.web.entities.Product;
import com.webcommerce.web.entities.ProductVariant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductDaoService productDaoService;

    @Autowired
    private ItemDaoService itemDaoService;

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductVariant> create(@RequestBody final ProductDto product) {
        return productDaoService.addProduct(product);
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> list() {
        return productDaoService.getProducts();
    }

    @PostMapping(path = "/items/{productVariantId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Item> addItems(@RequestBody List<Item> items, @PathVariable String productVariantId) {
        return itemDaoService.addItems(productVariantId, items);
    }
}
