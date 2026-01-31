package com.newzhxu.bandwagon.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data

public class GetRawUsageStatsR extends BaseR {
    private List<Daum> data;
    @JsonProperty("vm_type")
    private String vmType;
}
