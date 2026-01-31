package com.newzhxu.bandwagon;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;

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
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(
                Arrays.asList(
                        MediaType.APPLICATION_JSON,
                        MediaType.TEXT_PLAIN         // 关键！新增这一行
                )
        );
        return HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(
                        RestClient.builder()
                                .baseUrl(effectiveBaseUrl)
                                .messageConverters(httpMessageConverters ->
                                        httpMessageConverters.stream().filter(httpMessageConverter ->
                                                        httpMessageConverter instanceof MappingJackson2HttpMessageConverter)
                                                .findFirst()
                                                .ifPresent(c -> {
                                                    httpMessageConverters.set(httpMessageConverters.indexOf(c), new BandwagonMessaageConverter());
                                                }))
                                .build()))
                .build()
                .createClient(BandWagone.class);
    }
}
