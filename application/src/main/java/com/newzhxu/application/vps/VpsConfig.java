package com.newzhxu.application.vps;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VpsConfig {
    @Bean
    GroupedOpenApi groupedOpenApi() {
        return GroupedOpenApi.builder()
                .group("VPS 接口")
                .pathsToMatch("/vps/**")
                .build();
    }
}
