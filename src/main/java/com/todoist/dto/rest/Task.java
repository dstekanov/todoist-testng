package com.todoist.dto.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Task {

    /*
    {
    "comment_count": 10,
    "completed": true,
    "content": "My task",
    "due": {
        "date": "2016-09-01",
        "recurring": true,
        "datetime": "2016-09-01T09:00:00Z",
        "string": "tomorrow at 12",
        "timezone": "Europe/Moscow"
    },
    "id": 1234,
    "indent": 1,
    "label_ids": [
        124,
        125,
        128
    ],
    "order": 123,
    "priority": 1,
    "project_id": 2345,
    "url": "https://todoist.com/showTask?id=12345&sync_id=56789"
}
     */
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
