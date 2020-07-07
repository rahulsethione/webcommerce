package com.webcommerce.web.utils;

import com.mongodb.BulkUpdateRequestBuilder;
import com.webcommerce.web.entities.Parameter;
import com.webcommerce.web.enums.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ParameterManager {
    @Autowired
    MongoTemplate mongoTemplate;

    @PostConstruct
    void postConstruct() {
        BulkOperations bulkOperations = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, Parameter.class);

        for (Parameters value: Parameters.values()) {
            Query query = new Query(Criteria.where("paramName").is(value.getParameter().getParamName()));

            bulkOperations.upsert(
                    query,
                    Update.update("paramName", value.getParameter().getParamName())
                            .set("refData", value.getParameter().getRefData())
            );
        }

        bulkOperations.execute();

        List<Parameter> syncedParameters = mongoTemplate.findAll(Parameter.class);

        for(Parameter parameter: syncedParameters) {
            Parameter parameterToUpdate = Parameters.getParameterByName(parameter.getParamName());

            parameterToUpdate.setRefData(parameter.getRefData());
            parameterToUpdate.setDateCreated(parameter.getDateCreated());
            parameterToUpdate.setDateLastModified(parameter.getDateLastModified());
        }
    }

    public Date updateLastModifiedDate(Parameters parameters) {
        Parameter parameter = parameters.getParameter();
        Date now = new Date();

        parameter.setDateLastModified(now);

        Query query = new Query(Criteria.where("paramName").is(parameters.getParameter().getParamName()));
        Update update = Update.update("dateLastModified", now);

        mongoTemplate.updateMulti(query, update, Parameter.class);

        return now;
    }
}
