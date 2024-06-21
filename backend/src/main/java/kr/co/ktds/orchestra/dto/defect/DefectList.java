package kr.co.ktds.orchestra.dto.defect;

import lombok.Data;

@Data
public class DefectList {
    private Long no;
    private String testDgr;
    private String mdlNm;
    private String assigned;
    private String accTotal;
    private String accCmp;
    private String accCmpRate;
    private String weekTotal;
    private String weekCmp;
    private String weekCmpRate;
}
