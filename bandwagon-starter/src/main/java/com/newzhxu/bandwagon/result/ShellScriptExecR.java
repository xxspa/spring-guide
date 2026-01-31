package com.newzhxu.bandwagon.result;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ShellScriptExecR extends BaseR {
    private String log;
    @JsonProperty("output_stream_id")
    private String outputStreamId;
}
