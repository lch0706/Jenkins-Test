package kr.co.ktds.orchestra.controller;

import kr.co.ktds.orchestra.dto.dev.DevProgress;
import kr.co.ktds.orchestra.service.DevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value="/dev")
@CrossOrigin
public class DevController {

    @Autowired
    DevService devService;

    @RequestMapping(value="/list", method= RequestMethod.POST)
    public ResponseEntity<?> getProgressList(@RequestBody Map<String, String> body) {

        HashMap<String, Object> result = new HashMap<>();
        result.put("data", devService.getProgressList(body));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value="/status/module", method= RequestMethod.POST)
    public ResponseEntity<?> getModuleStatus(@RequestBody Map<String, String> body) {

        HashMap<String, Object> result = new HashMap<>();
        result.put("data", devService.getModuleStatus(body));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value="/status/assignee", method= RequestMethod.POST)
    public ResponseEntity<?> getAssigneeProgress(@RequestBody Map<String, String> body) {

        HashMap<String, Object> result = new HashMap<>();
        result.put("data", devService.getAssigneeStatus(body));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value="/status/complete", method= RequestMethod.POST)
    public ResponseEntity<?> getCompleteStatus(@RequestBody Map<String, String> body) {

        HashMap<String, Object> result = new HashMap<>();
        result.put("data", devService.getCompleteStatus(body));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value="/detail", method= RequestMethod.POST)
    public ResponseEntity<?> getProgressDetail(@RequestBody Map<String, String> body) {

        HashMap<String, Object> result = new HashMap<>();
        result.put("data", devService.getProgressDetail(body));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/status/daily", method = RequestMethod.POST)
    public ResponseEntity<?> getDailyStatus(@RequestBody Map<String, String> body) {

        HashMap<String, Object> result = new HashMap<>();
        result.put("data", devService.getDailyStatus(body));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
