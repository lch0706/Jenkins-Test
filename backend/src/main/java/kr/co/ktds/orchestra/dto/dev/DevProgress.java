package kr.co.ktds.orchestra.dto.dev;

import lombok.Data;

@Data
public class DevProgress {
//    private String projectId;
    private String tracker;
    private String status;
    private String module;
    private String assignee;
    private String startDate;
    private String endDate;
    private String accDate;
    private String type;
}
