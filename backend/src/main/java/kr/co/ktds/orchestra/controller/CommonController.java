package kr.co.ktds.orchestra.controller;

import kr.co.ktds.orchestra.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/common")
public class CommonController {

    @Autowired
    CommonService commonService;

    @RequestMapping(value = "/degree", method = RequestMethod.POST)
    public ResponseEntity<?> getTestDegree() {

        HashMap<String, Object> result = new HashMap<>();
        result.put("data", commonService.getTestDegree());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/module", method = RequestMethod.POST)
    public ResponseEntity<?> getModuleList(@RequestBody Map<String, Object> body) {

        HashMap<String, Object> result = new HashMap<>();
        result.put("data", commonService.getModuleList(body));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
