package com.newzhxu.bandwagon.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class SnapshotListR extends BaseR {
    private List<Snapshot> snapshots;
}
