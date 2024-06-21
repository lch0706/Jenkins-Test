package kr.co.ktds.orchestra.mapper;

import kr.co.ktds.orchestra.dto.matrix.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MatrixMapper {
    List<MatrixList> getMatrixList(String type, String region2, String keyword);
}
