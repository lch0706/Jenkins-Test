package kr.co.ktds.orchestra.service;

import kr.co.ktds.orchestra.dto.common.CompleteStatus;
import kr.co.ktds.orchestra.dto.common.DailyStatus;
import kr.co.ktds.orchestra.dto.common.ProgressDevList;
import kr.co.ktds.orchestra.dto.common.WeeklyProgressStatus;
import kr.co.ktds.orchestra.dto.dev.*;
import kr.co.ktds.orchestra.mapper.DevMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DevService {

    @Autowired
    DevMapper devMapper;

    public List<ProgressDevList> getProgressList(Map<String, String> body) {

        String startDate = body.get("startDate");
        String endDate = body.get("endDate");
        String accDate = body.get("accDate");
        String region = body.get("region");
        String keyword = body.get("keyword");
        String page = body.get("page");

        if(keyword != null)
            keyword = keyword.trim().replaceAll("\\p{Z}", "");

        List<ProgressDevList> progressLists = null;

        if(page != null)
            progressLists = devMapper.getProgressList2(startDate, endDate, accDate, region, keyword);
        else
            progressLists = devMapper.getProgressList(startDate, endDate, accDate, region, keyword);
        return progressLists;
    }

    public List<WeeklyProgressStatus> getModuleStatus(Map<String, String> body) {
        String startDate = body.get("startDate");
        String endDate = body.get("endDate");
        String region = body.get("region");
        String keyword = body.get("keyword");
        String page = body.get("page");

        if(keyword != null) {
            keyword = keyword.trim().replaceAll("\\p{Z}", ""); 
            System.out.println(keyword);
            System.out.println(region);
        }

        List<WeeklyProgressStatus> moduleStatuses = null;
        
        if(page != null)
            moduleStatuses = devMapper.getModuleStatus2(startDate, endDate, region, keyword);
        else    
            moduleStatuses = devMapper.getModuleStatus(startDate, endDate, region, keyword);

        return moduleStatuses;
    }

    public List<WeeklyProgressStatus> getAssigneeStatus(Map<String, String> body) {
        String startDate = body.get("startDate");
        String endDate = body.get("endDate");
        String region = body.get("region");
        String keyword = body.get("keyword");
        String page = body.get("page");

        if(keyword != null)
            keyword = keyword.trim().replaceAll("\\p{Z}", "");

        List<WeeklyProgressStatus> assigneeStatuses = null;

        if(page != null)
            assigneeStatuses = devMapper.getAssigneeStatus2(startDate, endDate, region, keyword);
        else
            assigneeStatuses = devMapper.getAssigneeStatus(startDate, endDate, region, keyword);
        return assigneeStatuses;
    }

    public List<CompleteStatus> getCompleteStatus(Map<String, String> body) {
        String accDate = body.get("accDate");
        String region = body.get("region");
        String keyword = body.get("keyword");
        String page = body.get("page");

        if(keyword != null) {
            keyword = keyword.trim().replaceAll("\\p{Z}", "");
        }

        List<CompleteStatus> completeStatuses = null;

        if(page != null)
            completeStatuses = devMapper.getCompleteStatus2(accDate, region, keyword);
        else
            completeStatuses = devMapper.getCompleteStatus(accDate, region, keyword);
        return completeStatuses;
    }

    public List<ProgressDetail> getProgressDetail(Map<String, String> body) {
        
        String tracker = body.get("tracker");
        String status = body.get("status");
        String module = body.get("module");
        String assignee = body.get("assignee");
        String startDate = body.get("startDate");
        String endDate = body.get("endDate");
        String accDate = body.get("accDate");
        String type = body.get("type");
        String page = body.get("page");

        List<ProgressDetail> progressDetails = null;

        if(page != null)
            progressDetails = devMapper.getProgressDetail2(tracker, status, module, assignee, startDate, endDate, accDate, type, page);
        else
            progressDetails = devMapper.getProgressDetail(tracker, status, module, assignee, startDate, endDate, accDate, type, page);
        
        return progressDetails;
    }

    public List<DailyStatus> getDailyStatus(Map<String, String> body) {
        String startDate = body.get("startDate");
        String endDate = body.get("endDate");
        String accDate = body.get("accDate");
        String region = body.get("region");
        String keyword = body.get("keyword");
        String testDgr = body.get("testDgr");
        String page = body.get("page");       
    
        if(keyword != null) {
            keyword = keyword.trim().replaceAll("\\p{Z}", "");
        }

        List<DailyStatus> resultList = null;

        if(page != null)
            resultList = devMapper.getDailyStatus2(testDgr, region, keyword, startDate, endDate, accDate);
        else
            resultList = devMapper.getDailyStatus(testDgr, region, keyword, startDate, endDate, accDate);
        return resultList;
    }
}
