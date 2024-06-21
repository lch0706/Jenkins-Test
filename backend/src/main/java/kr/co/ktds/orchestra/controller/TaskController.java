package kr.co.ktds.orchestra.controller;

import kr.co.ktds.orchestra.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value="/tasks")
public class TaskController {

//    Field injection is not recommended
//    @Autowired
//    TaskService taskService;
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Issue Risk ActionItem
    @RequestMapping(value="", method= RequestMethod.GET)
    public ResponseEntity<?> getIssueRiskCount() {

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data", taskService.getTaskCount());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
