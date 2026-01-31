package com.newzhxu.bandwagon.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data

public class GetServiceInfoR extends BaseR {
    @JsonProperty("vm_type")
    private String vmType;
    private String hostname;
    @JsonProperty("node_alias")
    private String nodeAlias;
    @JsonProperty("node_location_id")
    private String nodeLocationId;
    @JsonProperty("node_location")
    private String nodeLocation;
    @JsonProperty("node_datacenter")
    private String nodeDatacenter;
    @JsonProperty("location_ipv6_ready")
    private Boolean locationIpv6Ready;
    private String plan;
    @JsonProperty("plan_monthly_data")
    private Long planMonthlyData;
    @JsonProperty("monthly_data_multiplier")
    private Long monthlyDataMultiplier;
    @JsonProperty("plan_disk")
    private Long planDisk;
    @JsonProperty("plan_ram")
    private Long planRam;
    @JsonProperty("plan_swap")
    private Long planSwap;
    @JsonProperty("plan_max_ipv6s")
    private Long planMaxIpv6s;
    private String os;
    private String email;
    @JsonProperty("data_counter")
    private Long dataCounter;
    @JsonProperty("data_next_reset")
    private Long dataNextReset;
    @JsonProperty("ip_addresses")
    private List<String> ipAddresses;
    @JsonProperty("ipv6_sit_tunnel_endpoint")
    private String ipv6SitTunnelEndpoint;
    @JsonProperty("private_ip_addresses")
    private List<?> privateIpAddresses;
    @JsonProperty("ip_nullroutes")
    private List<?> ipNullroutes;
    private String iso1;
    private String iso2;
    @JsonProperty("available_isos")
    private List<String> availableIsos;
    @JsonProperty("plan_private_network_available")
    private Boolean planPrivateNetworkAvailable;
    @JsonProperty("location_private_network_available")
    private Boolean locationPrivateNetworkAvailable;
    @JsonProperty("rdns_api_available")
    private Boolean rdnsApiAvailable;
    private Map<String, String> ptr;
    private Boolean suspended;
    @JsonProperty("policy_violation")
    private Boolean policyViolation;
    @JsonProperty("suspension_count")
    private Long suspensionCount;
    @JsonProperty("total_abuse_points")
    private Long totalAbusePoints;
    @JsonProperty("max_abuse_points")
    private Long maxAbusePoints;
    @JsonProperty("plan_kiwivm_theme")
    private String planKiwivmTheme;
    @JsonProperty("free_ip_replacement_interval")
    private Long freeIpReplacementInterval;
}
