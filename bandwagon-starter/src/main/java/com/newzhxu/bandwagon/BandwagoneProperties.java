package com.newzhxu.bandwagon;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "bandwagone")
@Data
@Validated
public class BandwagoneProperties {
    @NotBlank(message = "URL 不能为空")
    private String url;
    @NotBlank(message = "VEID 不能为空")
    private String veId;
    @NotBlank(message = "API Key 不能为空")
    private String key;

    private boolean enabled;
}
