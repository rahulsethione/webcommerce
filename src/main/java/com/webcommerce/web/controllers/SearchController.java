package com.webcommerce.web.controllers;

import com.webcommerce.web.dao.SearchDaoService;
import com.webcommerce.web.entities.SearchItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchDaoService searchDaoService;

    @GetMapping(name = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SearchItem> getSearchResults(@RequestParam("query") String queryString) {
        return searchDaoService.productSearch(queryString);
    }
}
