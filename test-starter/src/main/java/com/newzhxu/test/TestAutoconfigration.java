package com.newzhxu.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties(TestProperties.class)
public class TestAutoconfigration {
    private static final Logger log = LoggerFactory.getLogger(TestAutoconfigration.class);

    @Bean
    DemoBean demoBean(TestProperties testProperties) {
        log.info("DemoBean created by TestAutoconfigration");
        log.info("TestProperties: {}", testProperties);
        return new DemoBean();
    }

    @Bean
    DemoBean1 demoBean1(TestProperties testProperties) {
        log.info("DemoBean1 created by TestAutoconfigration");
        log.info("TestProperties: {}", testProperties);
        return new DemoBean1();
    }

}
