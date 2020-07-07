package com.webcommerce.web.dao;

import com.webcommerce.web.entities.SearchItem;
import com.webcommerce.web.enums.Parameters;
import com.webcommerce.web.repositories.SearchRepository;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SearchDaoServiceImpl implements SearchDaoService {

    @Autowired
    private SearchRepository searchRepository;

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Override
    public void publishToElasticsearch(List<SearchItem> searchItems) {
        searchRepository.saveAll(searchItems);
    }

    @Override
    public List<SearchItem> productSearch(String searchString) {
        Map<String, Float> fieldsMap = Parameters.SEARCH_QUERY_FIELDS.getParameter().getRefData()
                .entrySet().stream().collect(Collectors.toMap(entry -> entry.getKey(), entry -> ((Double) entry.getValue()).floatValue()));

        QueryStringQueryBuilder stringQueryBuilder = new QueryStringQueryBuilder(searchString).fields(fieldsMap);
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();

        nativeSearchQueryBuilder
                .withQuery(stringQueryBuilder);

        return elasticsearchOperations.queryForList(nativeSearchQueryBuilder.build(), SearchItem.class);
    }
}
