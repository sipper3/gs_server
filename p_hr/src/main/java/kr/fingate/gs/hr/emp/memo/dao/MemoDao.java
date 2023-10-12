package kr.fingate.gs.hr.emp.memo.dao;

import com.github.pagehelper.Page;
import kr.fingate.gs.hr.emp.dto.EmpSearchDto;
import kr.fingate.gs.hr.emp.memo.dto.MemoDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemoDao {

    // 피고용인 교육정보조회
    Page<MemoDto> getMemoList(EmpSearchDto params) throws Exception;

}
