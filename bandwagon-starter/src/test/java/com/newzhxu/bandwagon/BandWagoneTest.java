package com.newzhxu.bandwagon;

import com.newzhxu.bandwagon.result.RestartR;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = BandwagonAutoconfigration.class)
class BandWagoneTest {


    @Autowired
    BandWagone bandWagone;

    @Test
    void start() {
        var start = bandWagone.start();
        Assertions.assertEquals(0, start.getError());
    }

    @Test
    void stop() {
        var stop = bandWagone.stop();
        Assertions.assertEquals(0, stop.getError());
    }

    @Test
    void restart() {
        RestartR restart = bandWagone.restart();
        Assertions.assertEquals(0, restart.getError());
    }

    @Test
    void kill() {
        var kill = bandWagone.kill();
        Assertions.assertEquals(0, kill.getError());
    }

    @Test
    void getServiceInfo() {
        var serviceInfo = bandWagone.getServiceInfo();
        Assertions.assertEquals(0, serviceInfo.getError());
    }

    @Test
    void getLiveServiceInfo() {
        var liveServiceInfo = bandWagone.getLiveServiceInfo();
        Assertions.assertEquals(0, liveServiceInfo.getError());
    }

    @Test
    void getAvailableOS() {
        var availableOS = bandWagone.getAvailableOS();
        Assertions.assertEquals(0, availableOS.getError());
    }

    @Test
    void GetSshKeys() {
        var sshKeys = bandWagone.getSshKeys();
        Assertions.assertEquals(0, sshKeys.getError());
    }

    @Test
    void resetRootPassword() {
        var resetRootPassword = bandWagone.resetRootPassword();
        Assertions.assertEquals(0, resetRootPassword.getError());
    }

    @Test
    void getUsageGraphs() {
        var usageGraphs = bandWagone.getUsageGraphs();
        Assertions.assertEquals(0, usageGraphs.getError());
    }

    @Test
    void getRawUsageStats() {
        var rawUsageStats = bandWagone.getRawUsageStats();
        Assertions.assertEquals(0, rawUsageStats.getError());
    }

    @Test
    void setHostname() {
        var setHostname = bandWagone.setHostname("example.com");
        Assertions.assertEquals(0, setHostname.getError());
    }

    @Test
    void basicShellExec() {
        var basicShellExec = bandWagone.basicShellExec("pwd");
        Assertions.assertEquals(0, basicShellExec.getError());
    }

    @Test
    void shellScriptExec() {
        var shellScriptExec = bandWagone.shellScriptExec("echo hello");
        Assertions.assertEquals(0, shellScriptExec.getError());
    }

    @Test
    void snapshotList() {
        var snapshotList = bandWagone.snapshotList();
        Assertions.assertEquals(0, snapshotList.getError());
    }

    @Test
    void privateIpGetAvailableIps() {
        var availableIps = bandWagone.privateIpGetAvailableIps();
        Assertions.assertEquals(0, availableIps.getError());
    }
}