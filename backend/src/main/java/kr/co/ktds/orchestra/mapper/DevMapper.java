package kr.co.ktds.orchestra.mapper;

import kr.co.ktds.orchestra.dto.common.CompleteStatus;
import kr.co.ktds.orchestra.dto.common.DailyStatus;
import kr.co.ktds.orchestra.dto.common.ProgressDevList;
import kr.co.ktds.orchestra.dto.common.WeeklyProgressStatus;
import kr.co.ktds.orchestra.dto.dev.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DevMapper {
    List<ProgressDevList> getProgressList(String startDate, String endDate, String accDate, String region, String keyword);
    List<ProgressDevList> getProgressList2(String startDate, String endDate, String accDate, String region, String keyword);
//    List<ProgressList> getProgressList(String start_date, String end_date, String acc_date, String);

    List<WeeklyProgressStatus> getModuleStatus(String startDate, String endDate, String region, String keyword);
    List<WeeklyProgressStatus> getModuleStatus2(String startDate, String endDate, String region, String keyword);

    List<WeeklyProgressStatus> getAssigneeStatus(String startDate, String endDate, String region, String keyword);
    List<WeeklyProgressStatus> getAssigneeStatus2(String startDate, String endDate, String region, String keyword);

    List<CompleteStatus> getCompleteStatus(String accDate, String region, String keyword);
    List<CompleteStatus> getCompleteStatus2(String accDate, String region, String keyword);

    List<ProgressDetail> getProgressDetail(String tracker, String status, String module, String assignee, String startDate, String endDate, String accDate, String type, String page);
    List<ProgressDetail> getProgressDetail2(String tracker, String status, String module, String assignee, String startDate, String endDate, String accDate, String type, String page);

    List<DailyStatus> getDailyStatus(String testDgr, String region, String keyword, String startDate, String endDate, String accDate);
    List<DailyStatus> getDailyStatus2(String testDgr, String region, String keyword, String startDate, String endDate, String accDate);
}
