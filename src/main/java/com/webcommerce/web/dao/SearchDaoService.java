package com.webcommerce.web.dao;

import com.webcommerce.web.entities.SearchItem;

import java.util.List;

public interface SearchDaoService {
    void publishToElasticsearch(List<SearchItem> searchItems);
    List<SearchItem> productSearch(String searchString);
}
