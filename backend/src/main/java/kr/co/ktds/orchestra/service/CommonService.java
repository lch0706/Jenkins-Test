package kr.co.ktds.orchestra.service;


import kr.co.ktds.orchestra.dto.common.DailyStatus;
import kr.co.ktds.orchestra.dto.common.ModuleStatus;
import kr.co.ktds.orchestra.dto.common.TestDegree;
import kr.co.ktds.orchestra.mapper.CommonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommonService {

    @Autowired
    CommonMapper commonMapper;

    public List<TestDegree> getTestDegree() {
        List<TestDegree> resultList = commonMapper.getTestDegree();
        return resultList;
    }

    public List<ModuleStatus> getModuleList(Map<String, Object> body) {
        List<ModuleStatus> resultList = commonMapper.getModuleList(body);
        return resultList;
    }
}
