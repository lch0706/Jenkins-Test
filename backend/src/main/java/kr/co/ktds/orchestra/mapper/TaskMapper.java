package kr.co.ktds.orchestra.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
@Mapper
public interface TaskMapper {
    List<Map<String, Object>> getTaskCount();
}
