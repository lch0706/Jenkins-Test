package kr.co.ktds.orchestra.dto.dev;

import lombok.Data;

@Data
public class DevDailyStatus {
    private String baseDate;
    private String planCnt;
    private String doneCnt;
}
