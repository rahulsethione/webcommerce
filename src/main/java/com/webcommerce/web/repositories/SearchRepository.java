package com.webcommerce.web.repositories;

import com.webcommerce.web.entities.SearchItem;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchRepository extends ElasticsearchCrudRepository<SearchItem, String> { }
