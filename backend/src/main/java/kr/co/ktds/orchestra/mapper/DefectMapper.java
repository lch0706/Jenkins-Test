package kr.co.ktds.orchestra.mapper;

import kr.co.ktds.orchestra.dto.common.CompleteStatus;
import kr.co.ktds.orchestra.dto.common.DailyStatus;
import kr.co.ktds.orchestra.dto.common.DetailModal;
import kr.co.ktds.orchestra.dto.defect.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface DefectMapper {
    List<DefectList> getDefectList(String testDgr, String region, String keyword, String startDate, String endDate, String accDate);
    List<CompleteStatus> getCompleteStatus(String testDgr, String region, String keyword, String startDate, String endDate, String accDate);
    List<ModuleDefect> getModuleDefect(String testDgr, String region, String keyword, String startDate, String endDate, String accDate);
    List<DetailModal> getDefectDetail(String testDgr, String module, String assignee, String startDate, String endDate, String accDate, String status, String type);
    List<DefectAssigneeStatus> getDefectAssigneeStatus(String testDgr, String region, String keyword, String startDate, String endDate, String accDate);
    List<DailyStatus> getDailyStatus(String testDgr, String region, String keyword, String startDate, String endDate, String accDate);
    List<Map<String, Object>> getModuleList(Map<String, Object> body);
}
