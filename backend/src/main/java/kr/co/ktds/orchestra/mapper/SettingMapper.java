package kr.co.ktds.orchestra.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface SettingMapper {
    List<Map<String, Object>> getProjects();
    List<Map<String, Object>> getSettings(int projectId);
    int setSettings(Map<String, Object> data, int projectId);
}
