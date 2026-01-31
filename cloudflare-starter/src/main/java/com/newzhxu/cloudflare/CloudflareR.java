package com.newzhxu.cloudflare;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.util.List;

@Data
public class CloudflareR<T> {
    private T result;
    @JsonProperty("result_info")
    private ResultInfo resultInfo;
    private Boolean success;
    private List<Info> errors;
    private List<Info> messages;

    @Data
    public static class ResultInfo {
        private Long page;
        @JsonProperty("per_page")
        private Long perPage;
        @JsonProperty("total_pages")
        private Long totalPages;
        private Long count;
        @JsonProperty("total_count")
        private Long totalCount;
    }

    @Data
    public static class Info {
        @Min(1000)
        private Integer code;
        private String message;
        @JsonProperty("documentation_url")
        private String documentationUrl;
        private Source source;


    }

    @Data
    public static class Source {
        private String pointer;
    }
}
