package com.newzhxu.cloudflare.dns.dns;

import com.newzhxu.cloudflare.dns.dns.records.Records;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Dns {
    Records records;
}
