package kr.co.ktds.orchestra.mapper;

import kr.co.ktds.orchestra.dto.common.DetailModal;
import kr.co.ktds.orchestra.dto.common.CompleteStatus;
import kr.co.ktds.orchestra.dto.common.ProgressList;
import kr.co.ktds.orchestra.dto.common.DailyStatus;
import kr.co.ktds.orchestra.dto.common.WeeklyProgressStatus;
import kr.co.ktds.orchestra.dto.test.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface TestMapper {
    List<ProgressList> getProgressList(String testDgr, String region, String keyword, String startDate, String endDate, String accDate);
    List<TestResultList> getResultList(String testDgr, String region, String keyword, String startDate, String endDate, String accDate);
    List<CompleteStatus> getCompleteStatus(String testDgr, String region, String keyword, String startDate, String endDate, String accDate);
    List<WeeklyProgressStatus> getModuleStatus(String testDgr, String region, String keyword, String startDate, String endDate, String accDate);
    List<DetailModal> getResultDetail(String testDgr, String module, String assignee, String startDate, String endDate, String accDate, String status, String type);
    List<DetailModal> getProgressDetail(String testDgr, String module, String assignee, String startDate, String endDate, String accDate, String status, String type);
    List<ResultStatus> getResultStatus(String testDgr, String region, String keyword, String startDate, String endDate, String accDate);
    List<DailyStatus> getDailyStatus(String testDgr, String region, String keyword, String startDate, String endDate, String accDate);
    List<Map<String, Object>> getIssueModuleList(Map<String, Object> body);
    List<Map<String, Object>> getModuleStatusData(Map<String, Object> body);
}
