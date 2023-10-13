package kr.fingate.gs.hr.emp.edu.dao;

import kr.fingate.gs.hr.vo.EmpEduVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EduModDao {

    // 피고용인 가족정보등록
    void insEdu(EmpEduVO params) throws Exception;

    // 피고용인 가족정보수정
    void updEdu(EmpEduVO params) throws Exception;

    // 피고용인 가족정보삭제
    void delEdu(EmpEduVO params) throws Exception;
}
