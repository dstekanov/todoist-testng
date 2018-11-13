package com.todoist.dto.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Project {

    @JsonProperty
    public String id;

    @JsonProperty
    public String name;

    @JsonProperty
    public String order;

    @JsonProperty
    public String indent;

    @JsonProperty
    public String commentCount;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
