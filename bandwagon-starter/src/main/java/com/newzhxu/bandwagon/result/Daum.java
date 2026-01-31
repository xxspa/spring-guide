package com.newzhxu.bandwagon.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Daum {
    private Long timestamp;
    @JsonProperty("cpu_usage")
    private Long cpuUsage;
    @JsonProperty("network_in_bytes")
    private Long networkInBytes;
    @JsonProperty("network_out_bytes")
    private Long networkOutBytes;
    @JsonProperty("disk_read_bytes")
    private Long diskReadBytes;
    @JsonProperty("disk_write_bytes")
    private Long diskWriteBytes;


}