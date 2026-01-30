package com.newzhxu.bandwagon;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@AutoConfiguration
@ConditionalOnClass(BandWagone.class)  // 你的接口类存在时才生效
@EnableConfigurationProperties(BandwagoneProperties.class)
public class BandwagonAutoconfigration {
    @Bean
    @ConditionalOnProperty(prefix = "bandwagone", name = "enabled", havingValue = "true", matchIfMissing = true)
    @ConditionalOnMissingBean(BandWagone.class)
        // 防止用户自己定义了同类型 bean
    BandWagone bandwagon(BandwagoneProperties properties) {
        log.info("BandwagonAutoconfiguration bandwagon");


        // 直接把 veid 和 api_key 拼到 baseUrl 里
        String effectiveBaseUrl = UriComponentsBuilder.fromUri(URI.create(properties.getUrl()))
                .queryParam("veid", properties.getVeId())
                .queryParam("api_key", properties.getKey())
                .toUriString();

        return HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(
                        RestClient.builder()
                                .baseUrl(effectiveBaseUrl)
                                .build()))
                .build()
                .createClient(BandWagone.class);
    }
}
