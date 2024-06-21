package kr.co.ktds.orchestra.dto.common;

import lombok.Data;

@Data
public class WeeklyProgressStatus {
    private Long no;
    private String trkNm;
    private String mdlNm;       //모듈명
    private String assigned;    //담당자
    private String weekPlan;    //계획
    private String weekPer;     //실적
    private String weekProg;    //진행중
    private String weekDly;     //지연
    private String weekPlanIds;    //계획
    private String weekPerIds;     //실적
    private String weekProgIds;    //진행중
    private String weekDlyIds;     //지연
}
