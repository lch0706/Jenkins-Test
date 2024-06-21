package kr.co.ktds.orchestra.dto.common;

import lombok.Data;

@Data
public class DetailModal {
    private Long no;
    private int id;
    private String trkNm;
    private String caseId;
    private String subject;
    private String assignee;
    private String startDate;
    private String endDate;
    private String closedDate;
    private String status;
}
