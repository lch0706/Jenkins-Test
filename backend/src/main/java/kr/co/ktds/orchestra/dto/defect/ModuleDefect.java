package kr.co.ktds.orchestra.dto.defect;

import lombok.Data;

@Data
public class ModuleDefect {
    private String mdlNm;
    private String total;
    private String pass;
    private String fail;
    private String block;
    private String cmpRate;
}
