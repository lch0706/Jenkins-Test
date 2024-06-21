package kr.co.ktds.orchestra.dto.task;

import lombok.Data;

@Data
public class IssueRiskDetail {
    private Long no;
    private Long id;
    private String subject;
    private String assignee;
    private String scheduleImpact;
    private String costImpact;
    private String qualityImpact;
    private String status;
    private String dueDate;
    private String causeSolution;
    private String content;
}
