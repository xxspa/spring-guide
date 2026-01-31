package com.newzhxu.cloudflare.dns.dns.records.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ListRecordsR {
    private String id;
    private String name;
    private String type;
    private String content;
    private Boolean proxiable;
    private Boolean proxied;
    private Long ttl;
    private Map<String, Object> settings;
    private Map<String, Object> meta;
    private String comment;
    private List<?> tags;
    @JsonProperty("created_on")
    private String createdOn;
    @JsonProperty("modified_on")
    private String modifiedOn;
    @JsonProperty("comment_modified_on")
    private String commentModifiedOn;
}
