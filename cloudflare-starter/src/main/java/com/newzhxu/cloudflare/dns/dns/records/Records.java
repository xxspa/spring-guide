package com.newzhxu.cloudflare.dns.dns.records;

import com.newzhxu.cloudflare.CloudflareR;
import com.newzhxu.cloudflare.dns.dns.records.Request.RecordRequest;
import com.newzhxu.cloudflare.dns.dns.records.result.DeleteRecordR;
import com.newzhxu.cloudflare.dns.dns.records.result.ListRecordsR;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.*;

import java.util.List;

@HttpExchange
public interface Records {
    @GetExchange("/zones/{zoneId}/dns_records")
    CloudflareR<List<ListRecordsR>> listRecords(@PathVariable String zoneId);

    @GetExchange("/zones/{zoneId}/dns_records/{recordId}")
    CloudflareR<ListRecordsR> dnsRecordDetails(@PathVariable String zoneId, @PathVariable String recordId);

    @PostExchange("/zones/{zoneId}/dns_records")
    CloudflareR<ListRecordsR> createDnsRecord(@PathVariable String zoneId, @RequestBody RecordRequest recordRequest);

    @PutExchange("/zones/{zoneId}/dns_records/{recordId}")
    CloudflareR<ListRecordsR> updateDnsRecord(@PathVariable String zoneId,
                                              @PathVariable String recordId,
                                              @RequestBody RecordRequest recordRequest);

    @PatchExchange("/zones/{zoneId}/dns_records/{recordId}")
    CloudflareR<ListRecordsR> patchDnsRecord(@PathVariable String zoneId,
                                             @PathVariable String recordId,
                                             @RequestBody RecordRequest recordRequest);

    @DeleteExchange("/zones/{zoneId}/dns_records/{recordId}")
    CloudflareR<DeleteRecordR> deleteDnsRecord(@PathVariable String zoneId, @PathVariable String recordId);

    @GetExchange("/zones/{zoneId}/dns_records/export")
    byte[] exportDnsRecords(@PathVariable String zoneId);

}
