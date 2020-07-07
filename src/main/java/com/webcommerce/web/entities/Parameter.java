package com.webcommerce.web.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Map;

@Document(collection = "parameters")
public class Parameter implements Persistable<String> {

    @NotNull
    @Field(name = "paramName")
    String paramName;

    @Field(name = "refData")
    Map<String, Object> refData;

    @Field(name = "dateCreated")
    Date dateCreated;

    @Field(name = "dateLastModified")
    Date dateLastModified;

    public Parameter(@NotNull String paramName, Map<String, Object> refData) {
        this.paramName = paramName;
        this.refData = refData;
    }

    public String getParamName() {
        return paramName;
    }

    public Map<String, Object> getRefData() {
        return refData;
    }

    public void setRefData(Map<String, Object> refData) {
        this.refData = refData;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateLastModified() {
        return dateLastModified;
    }

    public void setDateLastModified(Date dateLastModified) {
        this.dateLastModified = dateLastModified;
    }

    @Override
    public String getId() {
        return paramName;
    }

    @JsonIgnore
    @Override
    public boolean isNew() {
        return false;
    }
}
