package kr.co.ktds.orchestra.dto.matrix;

import lombok.Data;

@Data
public class MatrixList {
    private Long no;
    private String requirement;
    private String program;
    private String testcase;
    private String defect;
    private String moudleId;
    private String module;
    private String reqSubject;
    private String pgrSubject;
    private String tcSubject;
    private String dftSubject;
    private String reqId;
    private String pgrId;
    private String tcId;
}