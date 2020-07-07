package com.webcommerce.web.enums;

import com.webcommerce.web.entities.Parameter;
import com.webcommerce.web.entities.SearchItem;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public enum Parameters {
    SEARCH_JOB_LAST_RUN_DATE("search.job.lastRunDate", Collections.EMPTY_MAP),
    SEARCH_QUERY_FIELDS("search.query.fields", () -> {
        Map<String, Object> refDataMap = new HashMap<>();

        refDataMap.put("productName", 1f);
        refDataMap.put("categoryName", 2f);
        refDataMap.put("manufacturer", 3f);

        return refDataMap;
    });

    static Map<String, Parameter> parameterMap = new HashMap<>();

    Parameter parameter;

    Parameters(@NotNull String paramName, Map<String, Object> refData) {
        this.parameter = new Parameter(paramName, refData);
    }

    Parameters(@NotNull String paramName, Supplier<Map<String, Object>> refDataSupplier) {
        this.parameter = new Parameter(paramName, refDataSupplier.get());
    }

    static {
        for (Parameters value: Parameters.values()) {
            parameterMap.put(value.parameter.getParamName(), value.parameter);
        }
    }

    public static Parameter getParameterByName(String paramName) {
        return parameterMap.get(paramName);
    }

    public synchronized Parameter getParameter() {
        return parameter;
    }
}
