package kr.co.ktds.orchestra.service;

import kr.co.ktds.orchestra.dto.common.DetailModal;
import kr.co.ktds.orchestra.dto.common.CompleteStatus;
import kr.co.ktds.orchestra.dto.common.ProgressList;
import kr.co.ktds.orchestra.dto.common.DailyStatus;
import kr.co.ktds.orchestra.dto.common.WeeklyProgressStatus;
import kr.co.ktds.orchestra.dto.test.*;
import kr.co.ktds.orchestra.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TestService {

    @Autowired
    TestMapper testMapper;

    public List<ProgressList> getProgressList(Map<String, String> body) {
        String startDate = body.get("startDate");
        String endDate = body.get("endDate");
        String accDate = body.get("accDate");
        String region = body.get("region");
        String keyword = body.get("keyword");
        String testDgr = body.get("testDgr");
        if(keyword != null) {
            keyword = keyword.trim().replaceAll("\\p{Z}", "");
        }

        System.out.println(testDgr);
        System.out.println(region);

        List<ProgressList> progressList = testMapper.getProgressList(testDgr, region, keyword, startDate, endDate, accDate);
        return progressList;
    }

    public List<TestResultList> getResultList(Map<String, String> body) {
        String startDate = body.get("startDate");
        String endDate = body.get("endDate");
        String accDate = body.get("accDate");
        String region = body.get("region");
        String keyword = body.get("keyword");
        String testDgr = body.get("testDgr");
        if(keyword != null) {
            keyword = keyword.trim().replaceAll("\\p{Z}", "");
        }

        List<TestResultList> resultList = testMapper.getResultList(testDgr, region, keyword, startDate, endDate, accDate);
        return resultList;
    }

    public List<CompleteStatus> getCompleteStatus(Map<String, String> body) {
        String startDate = body.get("startDate");
        String endDate = body.get("endDate");
        String accDate = body.get("accDate");
        String region = body.get("region");
        String keyword = body.get("keyword");
        String testDgr = body.get("testDgr");
        if(keyword != null) {
            keyword = keyword.trim().replaceAll("\\p{Z}", "");
        }

        List<CompleteStatus> result = testMapper.getCompleteStatus(testDgr, region, keyword, startDate, endDate, accDate);
        return result;
    }

    public List<WeeklyProgressStatus> getModuleStatus(Map<String, String> body) {
        String startDate = body.get("startDate");
        String endDate = body.get("endDate");
        String accDate = body.get("accDate");
        String region = body.get("region");
        String keyword = body.get("keyword");
        String testDgr = body.get("testDgr");
        if(keyword != null) {
            keyword = keyword.trim().replaceAll("\\p{Z}", "");
        }

        List<WeeklyProgressStatus> resultList = testMapper.getModuleStatus(testDgr, region, keyword, startDate, endDate, accDate);
        return resultList;
    }

    public List<DetailModal> getResultDetail(Map<String, String> body) {
        String startDate = body.get("startDate");
        String endDate = body.get("endDate");
        String accDate = body.get("accDate");
        String module = body.get("module");
        String assignee = body.get("assignee");
        String testDgr = body.get("testDgr");
        String status = body.get("status");
        String type = body.get("type");

        List<DetailModal> resultList = testMapper.getResultDetail(testDgr, module, assignee, startDate, endDate,
                accDate, status, type);
        return resultList;
    }

    public List<DetailModal> getProgressDetail(Map<String, String> body) {
        String startDate = body.get("startDate");
        String endDate = body.get("endDate");
        String accDate = body.get("accDate");
        String module = body.get("module");
        String assignee = body.get("assignee");
        String testDgr = body.get("testDgr");
        String status = body.get("status");
        String type = body.get("type");

        List<DetailModal> resultList = testMapper.getProgressDetail(testDgr, module, assignee, startDate, endDate,
                accDate, status, type);
        return resultList;
    }

    public List<ResultStatus> getResultStatus(Map<String, String> body) {
        String startDate = body.get("startDate");
        String endDate = body.get("endDate");
        String accDate = body.get("accDate");
        String region = body.get("region");
        String keyword = body.get("keyword");
        String testDgr = body.get("testDgr");
        if(keyword != null) {
            keyword = keyword.trim().replaceAll("\\p{Z}", "");
        }

        List<ResultStatus> resultList = testMapper.getResultStatus(testDgr, region, keyword, startDate, endDate, accDate);
        return resultList;
    }

    public List<DailyStatus> getDailyStatus(Map<String, String> body) {
        String startDate = body.get("startDate");
        String endDate = body.get("endDate");
        String accDate = body.get("accDate");
        String region = body.get("region");
        String keyword = body.get("keyword");
        String testDgr = body.get("testDgr");
        if(keyword != null) {
            keyword = keyword.trim().replaceAll("\\p{Z}", "");
        }

        List<DailyStatus> resultList = testMapper.getDailyStatus(testDgr, region, keyword, startDate, endDate, accDate);
        return resultList;
    }

    public List<Map<String, Object>> getIssueModuleList(Map<String, Object> body) {
        List<Map<String, Object>> issueModuleList = testMapper.getIssueModuleList(body);
        return issueModuleList;
    }

    public List<Map<String, Object>> getModuleStatusData(Map<String, Object> body) {
        List<Map<String, Object>> moduleStatusData = testMapper.getModuleStatusData(body);
        return moduleStatusData;
    }
}
