package kr.fingate.gs.hr.emp.memo.dao;

import kr.fingate.gs.hr.vo.EmpMemoVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemoModDao {

    // 피고용인 메모정보등록
    void insMemo(EmpMemoVO params) throws Exception;

    // 피고용인 메모정보수정
    void updMemo(EmpMemoVO params) throws Exception;

    // 피고용인 메모정보삭제
    void delMemo(EmpMemoVO params) throws Exception;
}
