package kr.co.ktds.orchestra.controller;

import kr.co.ktds.orchestra.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping(value = "/list/progress", method = RequestMethod.POST)
    public ResponseEntity<?> getProgressList(@RequestBody Map<String, String> body) {

        HashMap<String, Object> result = new HashMap<>();
        result.put("data", testService.getProgressList(body));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/list/result", method = RequestMethod.POST)
    public ResponseEntity<?> getResultList(@RequestBody Map<String, String> body) {

        HashMap<String, Object> result = new HashMap<>();
        result.put("data", testService.getResultList(body));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/status/complete", method = RequestMethod.POST)
    public ResponseEntity<?> getCompleteStatus(@RequestBody Map<String, String> body) {

        HashMap<String, Object> result = new HashMap<>();
        result.put("data", testService.getCompleteStatus(body));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/status/module", method = RequestMethod.POST)
    public ResponseEntity<?> getModuleStatus(@RequestBody Map<String, String> body) {

        HashMap<String, Object> result = new HashMap<>();
        result.put("data", testService.getModuleStatus(body));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/detail/result", method = RequestMethod.POST)
    public ResponseEntity<?> getResultDetail(@RequestBody Map<String, String> body) {

        HashMap<String, Object> result = new HashMap<>();
        result.put("data",
                testService.getResultDetail(body));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/detail/progress", method = RequestMethod.POST)
    public ResponseEntity<?> getProgressDetail(@RequestBody Map<String, String> body) {

        HashMap<String, Object> result = new HashMap<>();
        result.put("data",
                testService.getProgressDetail(body));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/status/result", method = RequestMethod.POST)
    public ResponseEntity<?> getResultStatus(@RequestBody Map<String, String> body) {

        HashMap<String, Object> result = new HashMap<>();
        result.put("data", testService.getResultStatus(body));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/status/daily", method = RequestMethod.POST)
    public ResponseEntity<?> getDailyStatus(@RequestBody Map<String, String> body) {

        HashMap<String, Object> result = new HashMap<>();
        result.put("data", testService.getDailyStatus(body));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/modules", method = RequestMethod.POST)
    public ResponseEntity<?> getIssueModuleList(@RequestBody Map<String, Object> body) {

        HashMap<String, Object> result = new HashMap<>();
        result.put("data", testService.getIssueModuleList(body));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/modules/status", method = RequestMethod.POST)
    public ResponseEntity<?> getModuleStatusData(@RequestBody Map<String, Object> body) {

        HashMap<String, Object> result = new HashMap<>();
        result.put("data", testService.getModuleStatusData(body));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
