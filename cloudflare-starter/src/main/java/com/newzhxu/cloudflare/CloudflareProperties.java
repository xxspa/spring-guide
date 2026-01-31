package com.newzhxu.cloudflare;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cloudflare")
@Data
public class CloudflareProperties {
    private String url;
    private String apiKey;
}
