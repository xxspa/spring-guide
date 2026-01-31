package com.newzhxu.cloudflare.dns;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Dns {
    com.newzhxu.cloudflare.dns.dns.Dns dns;
}
