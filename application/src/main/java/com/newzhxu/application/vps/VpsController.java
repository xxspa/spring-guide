package com.newzhxu.application.vps;

import com.newzhxu.application.common.R;
import com.newzhxu.application.common.exception.NoContent;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vps")
@RequiredArgsConstructor
@Validated
@Tag(name = "VPS 接口", description = "VPS 相关接口")
public class VpsController {
    private final VpsService vps;

    @Operation(summary = "执行 VPS 任务", description = "执行指定的 VPS 脚本命令")
    @GetMapping("/exe")
    public R<Void> executeVpsTask(@RequestParam @Validated @NotBlank(message = "脚本不能为空") String command) {
        vps.executeVpsTask(command);
        return R.ok();
    }

    @Operation(summary = "获取执行日志", description = "获取 VPS 任务的执行日志")
    @GetMapping("/getResultLogs")
    public R<List<String>> getResultLogs() {
        List<String> logs = vps.getLogs();
        if (logs != null && logs.isEmpty()) {
            throw new NoContent();
        }
        return R.ok(logs);
    }

    @GetMapping
    @Operation(summary = "获取 Zones 列表", description = "获取 Cloudflare 的 Zones 列表")
    public R<String> zones() {
        String zones = vps.zones();
        return R.ok(zones);
    }
}
