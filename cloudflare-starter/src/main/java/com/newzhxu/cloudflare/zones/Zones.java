package com.newzhxu.cloudflare.zones;

import com.newzhxu.cloudflare.CloudflareR;
import com.newzhxu.cloudflare.zones.result.ZonesResponse;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface Zones {
    @GetExchange("/zones")
    CloudflareR<List<ZonesResponse>> listZones();
}
