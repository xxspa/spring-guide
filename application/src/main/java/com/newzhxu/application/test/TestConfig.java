package com.newzhxu.application.test;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
    @Bean
    GroupedOpenApi testOpenApi() {
        return GroupedOpenApi.builder()
                .group("test")
                .pathsToMatch("/test/**")
                .build();
    }

    @Bean
    GroupedOpenApi aOpenApi() {
        return GroupedOpenApi.builder()
                .group("a")
                .pathsToMatch("/a/**")
                .build();
    }
}
