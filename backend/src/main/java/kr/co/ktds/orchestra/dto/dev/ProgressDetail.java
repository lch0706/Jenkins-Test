package kr.co.ktds.orchestra.dto.dev;

import lombok.Data;

@Data
public class ProgressDetail {
    private Long no;
    private String id;
    private String trkNm;
    private String status;
    private String mdlNm;
    private String subject;
    private String assignee;
    private String startDate;
    private String endDate;
    private String closedDate;
    private String doneRatio;
}
