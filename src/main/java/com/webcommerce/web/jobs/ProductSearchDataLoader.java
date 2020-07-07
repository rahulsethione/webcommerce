package com.webcommerce.web.jobs;

import com.webcommerce.web.dao.ProductDaoService;
import com.webcommerce.web.dao.SearchDaoService;
import com.webcommerce.web.entities.Item;
import com.webcommerce.web.entities.Product;
import com.webcommerce.web.entities.ProductVariant;
import com.webcommerce.web.entities.SearchItem;
import com.webcommerce.web.enums.Parameters;
import com.webcommerce.web.utils.ParameterManager;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class ProductSearchDataLoader {

    private final Logger logger = LoggerFactory.getLogger(ProductSearchDataLoader.class);

    @Autowired
    private ProductDaoService productDaoService;

    @Autowired
    private SearchDaoService searchDaoService;

    @Autowired
    private ParameterManager parameterManager;

    private Date lastRunDate;

    @PostConstruct
    private void postConstruct() {
        lastRunDate = Optional.ofNullable(Parameters.SEARCH_JOB_LAST_RUN_DATE.getParameter().getDateLastModified())
                        .orElse(DateTime.now().minusDays(1).toDate());
    }

    @Scheduled(fixedDelay = 2 * 60 * 1000, initialDelay = 0)
    public void publishSearchItems() {
        logger.info("Executing Product Search Job: [{}] - {}", Thread.currentThread().getName(), new Date());

        List<ProductVariant> productVariants = productDaoService.getProductVariantsLastUpdated(lastRunDate);

        if(productVariants.isEmpty()) { return; }

        List<SearchItem> searchItems = new ArrayList<>();

        for (ProductVariant productVariant : productVariants) {
            if(CollectionUtils.isEmpty(productVariant.getItems())) { continue; }
            searchItems.addAll(flatten(productVariant));
        }

        searchDaoService.publishToElasticsearch(searchItems);
        lastRunDate = parameterManager.updateLastModifiedDate(Parameters.SEARCH_JOB_LAST_RUN_DATE);
    }

    private List<SearchItem> flatten(ProductVariant productVariant) {
        List<SearchItem> searchItemList = new ArrayList<>();

        for (Item item: productVariant.getItems()) {
            SearchItem searchItem = new SearchItem();

            fillProductFields(productVariant.getProduct(), searchItem);
            fillVariantDetails(productVariant, searchItem);
            fillItemDetails(item, searchItem);
            searchItem.setId(String.format("%s-%s-%s", searchItem.getProductId(), searchItem.getProductVariantId(), searchItem.getItemId()));

            searchItemList.add(searchItem);
        }

        return searchItemList;
    }

    private void fillProductFields(Product product, SearchItem searchItem) {
        searchItem.setProductId(product.getId());
        searchItem.setProductName(product.getName());
        searchItem.setManufacturer(product.getManufacturer());
        searchItem.setProductImages(StringUtils.collectionToCommaDelimitedString(product.getImages()));
        searchItem.setProductType(product.getProductType());
        searchItem.setCategoryId(product.getCategory().getId());
        searchItem.setCategoryName(product.getCategory().getName());
    }

    private void fillVariantDetails(ProductVariant productVariant, SearchItem searchItem) {
        searchItem.setProductVariantId(productVariant.getId());
        searchItem.setPrice(productVariant.getPrice());
        searchItem.setDiscount(productVariant.getDiscount());
        searchItem.setAttributes(productVariant.getAttributes());
        searchItem.setDateCreated(productVariant.getDateCreated());
        searchItem.setDateLastModified(productVariant.getDateLastModified());
    }

    private void fillItemDetails(Item item, SearchItem searchItem) {
        searchItem.setItemId(item.getId());
        searchItem.setProductCode(item.getId());
    }
}
