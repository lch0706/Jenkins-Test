package kr.co.ktds.orchestra.controller;

import kr.co.ktds.orchestra.service.DefectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value="/defect")
public class DefectController {

    @Autowired
    DefectService defectService;

    @RequestMapping(value="/list", method= RequestMethod.POST)
    public ResponseEntity<?> getProgressList(@RequestBody Map<String, String> body) {

        HashMap<String, Object> result = new HashMap<>();
        result.put("data", defectService.getDefectList(body));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value="/status/complete", method= RequestMethod.POST)
    public ResponseEntity<?> getCompleteStatus(@RequestBody Map<String, String> body) {

        HashMap<String, Object> result = new HashMap<>();
        result.put("data", defectService.getCompleteStatus(body));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value="/modules", method= RequestMethod.POST)
    public ResponseEntity<?> getModuleList(@RequestBody Map<String, Object> body) {

        HashMap<String, Object> result = new HashMap<>();
        result.put("data", defectService.getModuleList(body));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value="/status/module", method= RequestMethod.POST)
    public ResponseEntity<?> getModuleDefect(@RequestBody Map<String, String> body) {

        HashMap<String, Object> result = new HashMap<>();
        result.put("data", defectService.getModuleDefect(body));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value="/detail", method= RequestMethod.POST)
    public ResponseEntity<?> getDefectDetail(@RequestBody Map<String, String> body) {

        HashMap<String, Object> result = new HashMap<>();
        result.put("data", defectService.getDefectDetail(body));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value="/status/assignee", method= RequestMethod.POST)
    public ResponseEntity<?> getDefectAssigneeStatus(@RequestBody Map<String, String> body) {

        HashMap<String, Object> result = new HashMap<>();
        result.put("data", defectService.getDefectAssigneeStatus(body));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/status/daily", method = RequestMethod.POST)
    public ResponseEntity<?> getDailyStatus(@RequestBody Map<String, String> body) {

        HashMap<String, Object> result = new HashMap<>();
        result.put("data", defectService.getDailyStatus(body));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
