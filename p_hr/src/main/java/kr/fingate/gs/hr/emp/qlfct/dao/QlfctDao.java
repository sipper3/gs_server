package kr.fingate.gs.hr.emp.qlfct.dao;

import com.github.pagehelper.Page;
import kr.fingate.gs.hr.emp.dto.EmpSearchDto;
import kr.fingate.gs.hr.emp.qlfct.dto.QlfctDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QlfctDao {

    // 피고용인 자격정보조회
    Page<QlfctDto> getQlfctList(EmpSearchDto params) throws Exception;

}
