package kr.fingate.gs.hr.emp.qlfct.dao;

import kr.fingate.gs.hr.vo.EmpQlfctVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QlfctModDao {

    // 피고용인 자격정보등록
    public void insQlfct(EmpQlfctVO params) throws Exception;

    // 피고용인 자격정보수정
    public void updQlfct(EmpQlfctVO params) throws Exception;

    // 피고용인 자격정보삭제
    public void delQlfct(EmpQlfctVO params) throws Exception;
}
