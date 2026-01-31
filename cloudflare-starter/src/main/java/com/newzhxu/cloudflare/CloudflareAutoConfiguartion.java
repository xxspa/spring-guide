package com.newzhxu.cloudflare;

import com.newzhxu.cloudflare.converter.MyByteArrayMessageConverter;
import com.newzhxu.cloudflare.dns.Dns;
import com.newzhxu.cloudflare.dns.dns.records.Records;
import com.newzhxu.cloudflare.zones.Zones;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.List;

@AutoConfiguration
@EnableConfigurationProperties(CloudflareProperties.class)
@Slf4j
public class CloudflareAutoConfiguartion {
    @Bean
    public Cloudflare cloudflare(CloudflareProperties cloudflareProperties) {
        CloudflareMessageConverter converter = new CloudflareMessageConverter();

        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(
                        RestClient.builder()
                                .baseUrl(cloudflareProperties.getUrl())
                                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + cloudflareProperties.getApiKey())
                                .messageConverters(httpMessageConverters -> {
                                    httpMessageConverters.stream().filter(c -> c instanceof MappingJackson2HttpMessageConverter)
                                            .findFirst().ifPresent(c -> {
                                                httpMessageConverters.set(httpMessageConverters.indexOf(c), converter);
                                            });
                                    httpMessageConverters.stream().filter(c -> c instanceof ByteArrayHttpMessageConverter)
                                            .findFirst().ifPresent(c -> {
                                                httpMessageConverters.set(httpMessageConverters.indexOf(c), new MyByteArrayMessageConverter());
                                            });
                                })
                                .defaultStatusHandler(HttpStatusCode::isError, (request, response) -> {
                                    ResolvableType resolvableType = ResolvableType.forClassWithGenerics(CloudflareR.class, Void.class);
                                    @SuppressWarnings({"unchecked"})
                                    CloudflareR<Void> errorResult = (CloudflareR<Void>) converter.read(resolvableType.getType(), CloudflareR.class, response);
                                    List<CloudflareException.ErrorInfo> errorInfos = errorResult.getErrors().stream().map(e -> new CloudflareException.ErrorInfo().setCode(e.getCode()).setMessage(e.getMessage())).toList();
                                    throw new CloudflareException("Cloudflare API returned an error", errorInfos, request.getURI().toString());
                                })
                                .requestInterceptor((request, body, execution) ->
                                {
                                    ClientHttpResponse execute = execution.execute(request, body);
                                    return execute;
                                })
                                .build()))
                .build();
        Zones zones = factory.createClient(Zones.class);
        Records records = factory.createClient(Records.class);
        return new Cloudflare()
                .setZones(zones)
                .setDns(new Dns()
                        .setDns(new com.newzhxu.cloudflare.dns.dns.Dns()
                                .setRecords(records)
                        )
                );

    }

}
