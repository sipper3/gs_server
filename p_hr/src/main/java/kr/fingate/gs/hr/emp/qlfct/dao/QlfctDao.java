package kr.fingate.gs.hr.emp.qlfct.dao;

import com.github.pagehelper.Page;
import kr.fingate.gs.hr.emp.dto.EmpSearchDto;
import kr.fingate.gs.hr.emp.qlfct.dto.QlfctDto;
import kr.fingate.gs.hr.vo.EmpQlfctVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QlfctDao {

    // 피고용인 자격정보조회
    public Page<EmpQlfctVO> getQlfctList(EmpSearchDto params) throws Exception;

    // 피고용인 자격체크
    public List<QlfctDto> getQlfctDupCheck(QlfctDto params) throws Exception;
}
