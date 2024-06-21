package kr.co.ktds.orchestra.dto.defect;

import lombok.Data;

@Data
public class DefectAssigneeStatus {
    private Long no;
    private String assigned;            //담당자
    private String completionCount;     //조치건수
    private String remainCount;         //잔여건수
    private String doneids;             //완료건수 ID
    private String remainids;           //지연건수 ID
}
 