package kr.co.ktds.orchestra.service;

import kr.co.ktds.orchestra.dto.common.CompleteStatus;
import kr.co.ktds.orchestra.dto.common.DailyStatus;
import kr.co.ktds.orchestra.dto.common.DetailModal;
import kr.co.ktds.orchestra.dto.defect.*;
import kr.co.ktds.orchestra.mapper.DefectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DefectService {

    @Autowired
    DefectMapper defectMapper;

    public List<DefectList> getDefectList(Map<String, String> body) {
        String startDate = body.get("startDate");
        String endDate = body.get("endDate");
        String accDate = body.get("accDate");
        String region = body.get("region");
        String keyword = body.get("keyword");
        String testDgr = body.get("testDgr");
        if(keyword != null) {
            keyword = keyword.trim().replaceAll("\\p{Z}", "");
        }

        List<DefectList> list = defectMapper.getDefectList(testDgr, region, keyword, startDate, endDate, accDate);
        return list;
    }

    public List<Map<String, Object>> getModuleList(Map<String, Object> body) {

        List<Map<String, Object>> list = defectMapper.getModuleList(body);
        return list;
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

        List<CompleteStatus> result = defectMapper.getCompleteStatus(testDgr, region, keyword, startDate, endDate, accDate);
        return result;
    }
    public List<ModuleDefect> getModuleDefect(Map<String, String> body) {
        String startDate = body.get("startDate");
        String endDate = body.get("endDate");
        String accDate = body.get("accDate");
        String region = body.get("region");
        String keyword = body.get("keyword");
        String testDgr = body.get("testDgr");
        if(keyword != null) {
            keyword = keyword.trim().replaceAll("\\p{Z}", "");
        }

        List<ModuleDefect> result = defectMapper.getModuleDefect(testDgr, region, keyword, startDate, endDate, accDate);
        return result;
    }

    public List<DetailModal> getDefectDetail(Map<String, String> body) {
        String startDate = body.get("startDate");
        String endDate = body.get("endDate");
        String accDate = body.get("accDate");
        String module = body.get("module");
        String assignee = body.get("assignee");
        String testDgr = body.get("testDgr");
        String status = body.get("status");
        String type = body.get("type");

        List<DetailModal> resultList = defectMapper.getDefectDetail(testDgr, module, assignee, startDate, endDate, accDate, status, type);
        return resultList;
    }

    public List<DefectAssigneeStatus> getDefectAssigneeStatus(Map<String, String> body) {
        String startDate = body.get("startDate");
        String endDate = body.get("endDate");
        String accDate = body.get("accDate");
        String region = body.get("region");
        String keyword = body.get("keyword");
        String testDgr = body.get("testDgr");
        if(keyword != null) {
            keyword = keyword.trim().replaceAll("\\p{Z}", "");
        }

        List<DefectAssigneeStatus> result = defectMapper.getDefectAssigneeStatus(testDgr, region, keyword, startDate, endDate, accDate);
        return result;
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

        List<DailyStatus> resultList = defectMapper.getDailyStatus(testDgr, region, keyword, startDate, endDate, accDate);
        return resultList;
    }
}
