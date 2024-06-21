package kr.co.ktds.orchestra.dto.test;

import lombok.Data;

@Data
public class TestResultList {
    private Long no;
    private String trkNm;
    private String mdlNm;
    private String assigned;
    private String accTotal;
    private String accPassRate;
    private String accPass;
    private String accFail;
    private String accBlock;
    private String weekTotal;
    private String weekPassRate;
    private String weekPass;
    private String weekFail;
    private String weekBlock;
}
