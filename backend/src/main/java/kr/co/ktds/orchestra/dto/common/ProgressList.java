package kr.co.ktds.orchestra.dto.common;

import lombok.Data;

@Data
public class ProgressList {
    private Long no;
    private String trkNm;       //차수
    private String mdlNm;       //모듈명
    private String assigned;    //담당자
    private String accPlan;     //누적계획
    private String accPer;      //누적실적
    private String accProg;     //누적진행
    private String accDly;      //누적지연
    private String accCmpRate;  //누적완료율
    private String weekPlan;    //주간계획
    private String weekPer;     //주간실적
    private String weekProg;    //주간진행
    private String weekDly;     //주간지연
    private String weekCmpRate; //주간완료율
    private String allRes;      //전체잔여
    private String allTotal;    //전체

}
