package com.webcommerce.web.dao;

import com.webcommerce.web.entities.Item;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ItemDaoService {
    @Transactional List<Item> addItems(String productVariantId, List<Item> items);
}
