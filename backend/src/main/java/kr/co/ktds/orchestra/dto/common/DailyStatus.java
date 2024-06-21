package kr.co.ktds.orchestra.dto.common;

import lombok.Data;

@Data
public class DailyStatus {
    private String baseDate;
    private String planCnt;
    private String doneCnt;
    private String planList;
    private String doneList;
}
