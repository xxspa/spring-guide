package com.newzhxu.cloudflare;

import com.newzhxu.cloudflare.dns.Dns;
import com.newzhxu.cloudflare.zones.Zones;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Cloudflare {
    private Zones zones;
    private Dns dns;
}
