package kr.co.ktds.orchestra.service;

import kr.co.ktds.orchestra.dto.matrix.*;
import kr.co.ktds.orchestra.mapper.MatrixMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MatrixService {

    @Autowired
    MatrixMapper matrixMapper;

    public List<MatrixList> getMatrixList(Map<String, String> body) {
        String region2 = body.get("region2");
        String keyword = body.get("keyword");
        String type = body.get("region");

        if(keyword != null) {
            keyword = keyword.trim().replaceAll("\\p{Z}", "");
        }

        List<MatrixList> list = matrixMapper.getMatrixList(type, region2, keyword);
        return list;
    }
}
