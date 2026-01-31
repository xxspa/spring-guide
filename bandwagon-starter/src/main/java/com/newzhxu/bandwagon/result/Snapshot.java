package com.newzhxu.bandwagon.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Snapshot {
    private String fileName;
    private String os;
    private String description;
    private String size;
    private String md5;
    private Boolean sticky;
    private Long uncompressed;
    private Long purgesIn;
    private String downloadLink;
    @JsonProperty("downloadLinkSSL")
    private String downloadLinkSsl;
}
