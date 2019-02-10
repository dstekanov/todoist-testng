package com.todoist.dto.rest;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * "due": {
 * "date": "2016-09-01",
 * "recurring": true,
 * "datetime": "2016-09-01T09:00:00Z",
 * "string": "tomorrow at 12",
 * "timezone": "Europe/Moscow"
 * }
 */
public class Due {

    /**
     * Date in format YYYY-MM-DD corrected to user’s timezone.
     */
    @JsonProperty
    public String date;

    @JsonProperty
    public Boolean recurring;

    /**
     * Only returned if exact due time set (i.e. it’s not a whole-day task), date and time in RFC3339 format in UTC.
     */
    @JsonProperty
    public String datetime;

    @JsonProperty
    public String string;

    /**
     * Only returned if exact due time set, user’s timezone definition either
     * in tzdata-compatible format (“Europe/Berlin”) or as a string specifying east of UTC offset
     * as “UTC±HH:MM” (i.e. “UTC-01:00”).
     */
    @JsonProperty
    public String timezone;
}
