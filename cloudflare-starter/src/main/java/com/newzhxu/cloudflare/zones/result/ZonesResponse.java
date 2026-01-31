package com.newzhxu.cloudflare.zones.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ZonesResponse {
    private String id;
    private String name;
    private String status;
    private Boolean paused;
    private String type;
    @JsonProperty("development_mode")
    private Long developmentMode;
    @JsonProperty("name_servers")
    private List<String> nameServers;
    @JsonProperty("original_name_servers")
    private Object originalNameServers;
    @JsonProperty("original_registrar")
    private Object originalRegistrar;
    @JsonProperty("original_dnshost")
    private Object originalDnshost;
    @JsonProperty("modified_on")
    private String modifiedOn;
    @JsonProperty("created_on")
    private String createdOn;
    @JsonProperty("activated_on")
    private String activatedOn;
    @JsonProperty("vanity_name_servers")
    private List<?> vanityNameServers;
    @JsonProperty("vanity_name_servers_ips")
    private Object vanityNameServersIps;
    private Meta meta;
    private Owner owner;
    private Account account;
    private Tenant tenant;
    @JsonProperty("tenant_unit")
    private TenantUnit tenantUnit;
    private List<String> permissions;
    private Plan plan;

    @Data
    public static class Meta {
        private Long step;
        @JsonProperty("custom_certificate_quota")
        private Long customCertificateQuota;
        @JsonProperty("page_rule_quota")
        private Long pageRuleQuota;
        @JsonProperty("phishing_detected")
        private Boolean phishingDetected;
    }

    @Data
    public static class Owner {
        private Object id;
        private String type;
        private Object email;
    }

    @Data
    public static class Account {
        private String id;
        private String name;
    }

    @Data
    public static class Tenant {
        private Object id;
        private Object name;
    }

    @Data
    public static class TenantUnit {
        private Object id;


    }

    @Data
    public static class Plan {
        private String id;
        private String name;
        private Long price;
        private String currency;
        private String frequency;
        @JsonProperty("is_subscribed")
        private Boolean isSubscribed;
        @JsonProperty("can_subscribe")
        private Boolean canSubscribe;
        @JsonProperty("legacy_id")
        private String legacyId;
        @JsonProperty("legacy_discount")
        private Boolean legacyDiscount;
        @JsonProperty("externally_managed")
        private Boolean externallyManaged;
    }
}
