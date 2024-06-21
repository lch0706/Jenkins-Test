package kr.co.ktds.orchestra.dto.task;

import lombok.Data;

@Data
public class TaskCount {
    private String trackerName;
    private Integer totalCount;
    private Integer progressCount;
    private Integer completeCount;
    private Integer cancelHoldCount;
    private Integer ratio;
}
