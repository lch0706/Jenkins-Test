package kr.co.ktds.orchestra.mapper;

import kr.co.ktds.orchestra.dto.common.DailyStatus;
import kr.co.ktds.orchestra.dto.common.ModuleStatus;
import kr.co.ktds.orchestra.dto.common.TestDegree;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface CommonMapper {
    List<TestDegree> getTestDegree();
    List<ModuleStatus> getModuleList(Map<String, Object> body);
}
