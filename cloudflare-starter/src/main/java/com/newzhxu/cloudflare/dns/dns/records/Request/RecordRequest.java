package com.newzhxu.cloudflare.dns.dns.records.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class RecordRequest {
    @NotBlank(message = "记录名称不能为空")
    @Size(min = 1, max = 255, message = "名称长度必须在 1 到 255 个字符之间")
    private String name;
    @Min(value = 1, message = "TTL 最小为 1（表示 automatic）")
    @Max(value = 86400, message = "TTL 最大为 86400 秒")
    private Long ttl = 1L;
    @NotBlank(message = "记录类型不能为空")
    private String type;
    @Size(max = 1024, message = "备注长度不能超过 1024 个字符")
    private String comment;
    @NotBlank(message = "内容不能为空")
    private String content;
    private Boolean proxied = false;
    /**
     * 高级设置（仅对 proxied = true 有效）
     */
    @Valid  // 嵌套对象也需要校验
    private Settings settings;
    @Size(max = 10, message = "标签数量不能超过 10 个")
    private List<@Size(min = 1, max = 64, message = "每个标签长度 1~64 字符") String> tags;


    @Data
    public static class Settings {
        @JsonProperty("ipv4_only")
        private Boolean ipv4Only;
        @JsonProperty("ipv6_only")
        private Boolean ipv6Only;
    }

}
