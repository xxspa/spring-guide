package com.newzhxu.application.vps;

import com.newzhxu.bandwagon.BandWagone;
import com.newzhxu.bandwagon.result.ShellScriptExecR;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Slf4j
@RequiredArgsConstructor
public class VpsService {
    private final BandWagone bandWagone;
    private final List<Path> resultLogs = new CopyOnWriteArrayList<>();

    public void executeVpsTask(String shellScript) {
        log.info("Executing VPS task...");
        // Add your VPS related logic here, e.g., interacting with BandWagone API
        ShellScriptExecR r = bandWagone.shellScriptExec(shellScript);
        Assert.isTrue(r.getError() == 0, "Failed to execute shell script on VPS: " + r.getMessage());
        log.info("VPS task completed.");
        resultLogs.add(r.getLog());
    }

    public List<String> getLogs() {
        List<String> list = resultLogs.stream().map(l -> bandWagone.basicShellExec("cat " + l).getMessage())
                .toList();

        return list;
    }
}
