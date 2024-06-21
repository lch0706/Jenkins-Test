package kr.co.ktds.orchestra.controller;

import kr.co.ktds.orchestra.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value="/setting")
public class SettingController {

    @Autowired
    SettingService settingService;

    @RequestMapping(value="/projects", method= RequestMethod.POST)
    public ResponseEntity<?> getProjects() {
        return new ResponseEntity<>(settingService.getProjects(), HttpStatus.OK);
    }

    @RequestMapping(value="/projects/{projectId}", method= RequestMethod.GET)
    public ResponseEntity<?> getSettings(@PathVariable("projectId") int projectId) {
        return new ResponseEntity<>(settingService.getSettings(projectId), HttpStatus.OK);
    }

    @RequestMapping(value="/projects/{projectId}", method= RequestMethod.POST)
    public ResponseEntity<?> saveSettings(@PathVariable("projectId") int projectId, @RequestBody List<Map<String, Object>> body) {
        return new ResponseEntity<>(settingService.setSettings(body, projectId), HttpStatus.OK);
    }
}
