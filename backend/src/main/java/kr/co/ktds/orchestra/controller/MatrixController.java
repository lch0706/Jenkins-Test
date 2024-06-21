package kr.co.ktds.orchestra.controller;

import kr.co.ktds.orchestra.service.MatrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping(value="/matrix")
public class MatrixController {

    @Autowired
    MatrixService matrixService;

    @RequestMapping(value="/list", method= RequestMethod.POST)
    public ResponseEntity<?> getMatrixList(@RequestBody Map<String, String> body) {

        HashMap<String, Object> result = new HashMap<>();
        result.put("data", matrixService.getMatrixList(body));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
