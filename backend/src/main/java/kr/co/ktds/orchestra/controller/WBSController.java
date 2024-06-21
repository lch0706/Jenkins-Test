package kr.co.ktds.orchestra.controller;

import kr.co.ktds.orchestra.service.WBSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value = "/wbs")
public class WBSController {
    @Autowired
    WBSService wbsService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> getWBSList(@RequestBody(required=false) String date) {

        HashMap<String, Object> result = new HashMap<>();
        result.put("data", wbsService.getWBSList(date));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
