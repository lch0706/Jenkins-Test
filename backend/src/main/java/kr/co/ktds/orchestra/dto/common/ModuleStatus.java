package kr.co.ktds.orchestra.dto.common;

import lombok.Data;

@Data
public class ModuleStatus {
    private String mdlNm;           //모듈명
    private String status;
    private String statusId;
    private String cnt;
    private String ids;
    private String parentNm;
}
