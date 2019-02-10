package com.todoist.dto.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * {
 * "comment_count": 10,
 * "completed": true,
 * "content": "My task",
 * "due": {
 * "date": "2016-09-01",
 * "recurring": true,
 * "datetime": "2016-09-01T09:00:00Z",
 * "string": "tomorrow at 12",
 * "timezone": "Europe/Moscow"
 * },
 * "id": 1234,
 * "indent": 1,
 * "label_ids": [
 * 124,
 * 125,
 * 128
 * ],
 * "order": 123,
 * "priority": 1,
 * "project_id": 2345,
 * "url": "https://todoist.com/showTask?id=12345&sync_id=56789"
 * }
 */
public class Task {

    @JsonProperty
    public Long id;

    @JsonProperty
    public Long projectId;

    @JsonProperty
    public String content;

    @JsonProperty
    public Integer commentCount;

    @JsonProperty
    public Boolean completed;

    @JsonProperty
    public Due due;

    @JsonProperty
    public Integer indent;

    @JsonProperty
    public List<Integer> labelIds;

    @JsonProperty
    public Integer order;

    @JsonProperty
    public Integer priority;

    @JsonProperty
    public String url;

    @Override
    public String toString() {

        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
