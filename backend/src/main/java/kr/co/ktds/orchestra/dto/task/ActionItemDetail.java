package kr.co.ktds.orchestra.dto.task;

import lombok.Data;

@Data
public class ActionItemDetail {
    private Long no;
    private Long id;
    private String subject;
    private String reqDate;
    private String reqContent;
    private String resultStatus;
    private String deadline;
    private String assignee;
    private String status;
    private String dueDate;
}
