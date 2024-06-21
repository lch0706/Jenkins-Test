package kr.co.ktds.orchestra.dto.defect;

import lombok.Data;

@Data
public class DefectDailyStatus {
    private String baseDate;
    private String planCnt;
    private String doneCnt;
}
