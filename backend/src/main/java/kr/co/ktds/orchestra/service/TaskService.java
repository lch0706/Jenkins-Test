package kr.co.ktds.orchestra.service;

import kr.co.ktds.orchestra.dto.task.*;
import kr.co.ktds.orchestra.mapper.TaskMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskService {

    private TaskMapper taskMapper;

    public TaskService(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    public List<Map<String, Object>> getTaskCount() {
        List<Map<String, Object>> taskCounts = taskMapper.getTaskCount();
        return taskCounts;
    }

}
