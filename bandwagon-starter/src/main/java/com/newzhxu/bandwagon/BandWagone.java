package com.newzhxu.bandwagon;

import com.newzhxu.bandwagon.result.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
public interface BandWagone {
    @GetExchange("/start")
    StartR start();

    @GetExchange("/stop")
    StopR stop();

    @GetExchange("/restart")
    RestartR restart();

    @GetExchange("/kill")
    KillR kill();

    @GetExchange("/getServiceInfo")
    GetServiceInfoR getServiceInfo();

    @GetExchange("/getLiveServiceInfo")
    GetLiveServiceInfoR getLiveServiceInfo();

    @GetExchange("/getAvailableOS")
    GetAvailableOSR getAvailableOS();

    @GetExchange("/reinstallOS")
    ReinstallOSR reinstallOS(@RequestParam String os);

    @GetExchange("updateSshKeys")
    UpdateSshKeysR updateSshKeys(@RequestParam("ssh_keys") String sshKeys);

    @GetExchange("/getSshKeys")
    GetSshKeysR getSshKeys();

    @GetExchange("/resetRootPassword")
    ResetRootPasswordR resetRootPassword();

    /**
     * Use getUsageGraphs instead
     *
     * @return
     */
    @Deprecated()
    @GetExchange("/getUsageGraphs")
    GetUsageGraphsR getUsageGraphs();

    @GetExchange("/getRawUsageStats")
    GetRawUsageStatsR getRawUsageStats();

    @GetExchange("/setHostname")
    SetHostnameR setHostname(@RequestParam("newHostname") String newHostname);

    @GetExchange("/setPTR")
    SetPTRR setPTR(@RequestParam("ip") String ip, @RequestParam("ptr") String ptr);

    @GetExchange("/iso/mount")
    IsoMountR isoMount(@RequestParam("iso") String iso);

    @GetExchange("/iso/unmount")
    IsoUnmountR isoUnmount();

    @GetExchange("basicShell/cd")
    BasicShellCdR basicShellCd(@RequestParam("currentDir") String currentDir, @RequestParam("newDir") String newDir);

    @GetExchange("/basicShell/exec")
    BasicShellExecR basicShellExec(@RequestParam("command") String command);

    @GetExchange("/shellScript/exec")
    ShellScriptExecR shellScriptExec(@RequestParam("script") String script);

    @GetExchange("/snapshot/create")
    SnapshotCreateR snapshotCreate(@RequestParam(value = "description", required = false) String description);

    @GetExchange("/snapshot/list")
    SnapshotListR snapshotList();

    @GetExchange("/snapshot/delete")
    SnapshotDeleteR snapshotDelete(@RequestParam("snapshot") String snapshot);

    @GetExchange("/snapshot/restore")
    SnapshotRestoreR snapshotRestore(@RequestParam("snapshot") String snapshot);

    @GetExchange("/snapshot/toggleSticky")
    SnapshotToggleStickyR snapshotToggleSticky(@RequestParam("snapshot") String snapshot, @RequestParam("sticky") String sticky);

    @GetExchange("/snapshot/export")
    SnapshotExportR snapshotExport(@RequestParam("snapshot") String snapshot);

    @GetExchange("/snapshot/import")
    SnapshotImportR snapshotImport(@RequestParam("sourceVeid") String sourceVeid,
                                   @RequestParam("sourceToken") String sourceToken
    );

    @GetExchange("/backup/list")
    BackupListR backupList();

    @GetExchange("/backup/copyToSnapshot")
    BackupCopyToSnapshotR backupCopyToSnapshot(@RequestParam("backupToken") String backupToken);

    @GetExchange("/ipv6/add")
    Ipv6AddR ipv6Add();

    @GetExchange("/ipv6/delete")
    Ipv6DeleteR ipv6Delete(@RequestParam("ip") String ip);

    @GetExchange("/migrate/getLocations")
    MigrateGetLocationsR migrateGetLocations();

    @GetExchange("migrate/start")
    MigrateStartR migrateStart(@RequestParam("location") String location);

    @GetExchange("/cloneFromExternalServer")
    CloneFromExternalServerR cloneFromExternalServer(@RequestParam("externalServerIP") String externalServerIP,
                                                     @RequestParam("externalServerSSHport") String externalServerSSHport,
                                                     @RequestParam("externalServerRootPassword") String externalServerRootPassword);

    @GetExchange("/getSuspensionDetails")
    GetSuspensionDetailsR getSuspensionDetails();

    @GetExchange("/getPolicyViolations")
    GetPolicyViolationsR getPolicyViolations();

    @GetExchange("/unsuspend")
    UnsuspendR unsuspend(@RequestParam("record_id") String recordId);

    @GetExchange("/resolvePolicyViolation")
    ResolvePolicyViolationR resolvePolicyViolation(@RequestParam("record_id") String recordId);

    @GetExchange("/getRateLimitStatus")
    GetRateLimitStatusR getRateLimitStatus();

    @GetExchange("/privateIp/getAvailableIps")
    PrivateIpGetAvailableIpsR privateIpGetAvailableIps();

    @GetExchange("/privateIp/assign")
    PrivateIpAssignR privateIpAssign(@RequestParam(value = "ip", required = false) String ip);

    @GetExchange("/privateIp/delete")
    PrivateIpDeleteR privateIpDelete(@RequestParam("ip") String ip);

    @GetExchange("/kiwivm/getNotificationPreferences")
    KiwiVmGetNotificationPreferencesR kiwiVmGetNotificationPreferences();

    @GetExchange("/kiwivm/setNotificationPreferences")
    KiwiVmSetNotificationPreferencesR kiwiVmSetNotificationPreferences(
            @RequestParam("json_notification_preferences") String jsonNotificationPreferences
    );

}


