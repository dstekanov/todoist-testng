package com.todoist.dto.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Project {

    @JsonProperty
    public Long id;

    @JsonProperty
    public String name;

    @JsonProperty
    public Integer order;

    @JsonProperty
    public Integer indent;

    @JsonProperty
    public Integer commentCount;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
