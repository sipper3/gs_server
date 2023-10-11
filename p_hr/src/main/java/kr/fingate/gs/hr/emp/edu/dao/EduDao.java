package kr.fingate.gs.hr.emp.edu.dao;

import com.github.pagehelper.Page;
import kr.fingate.gs.hr.emp.dto.EmpSearchDto;
import kr.fingate.gs.hr.emp.edu.dto.EduDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EduDao {

    // 피고용인 교육정보조회
    Page<EduDto> getEduList(EmpSearchDto params) throws Exception;

}
