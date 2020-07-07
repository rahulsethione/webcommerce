package com.webcommerce.web.dao;

import com.webcommerce.web.entities.Item;
import com.webcommerce.web.entities.ProductVariant;
import com.webcommerce.web.repositories.ItemRepository;
import com.webcommerce.web.repositories.ProductVariantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ItemDaoServiceImpl implements ItemDaoService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ProductVariantRepository productVariantRepository;

    @Override
    @Transactional
    public List<Item> addItems(String productVariantId, List<Item> items) {

        ProductVariant productVariant = productVariantRepository.findById(productVariantId).orElse(null);

        if(Objects.isNull(productVariant)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product variant does not exist");
        }

        List<Item> newItems = itemRepository.saveAll(items);
        Set<Item> itemSet = CollectionUtils.isEmpty(productVariant.getItems()) ? new HashSet<>() : productVariant.getItems().stream().collect(Collectors.toSet());

        itemSet.addAll(newItems);
        productVariant.setItems(itemSet);
        productVariantRepository.save(productVariant);

        return newItems;
    }
}
