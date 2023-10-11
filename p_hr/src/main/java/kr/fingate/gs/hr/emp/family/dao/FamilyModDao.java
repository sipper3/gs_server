package kr.fingate.gs.hr.emp.family.dao;

import kr.fingate.gs.hr.vo.EmpFamilyVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FamilyModDao {
    // 피고용인 교육정보등록
    void insFamily(EmpFamilyVO params) throws Exception;

    // 피고용인 교육정보수정
    void updFamily(EmpFamilyVO params) throws Exception;

    // 피고용인 교육정보삭제
    void delFamily(EmpFamilyVO params) throws Exception;
}
