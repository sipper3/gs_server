package kr.fingate.gs.hr.emp.family.dao;

import com.github.pagehelper.Page;
import kr.fingate.gs.hr.emp.dto.EmpSearchDto;
import kr.fingate.gs.hr.emp.family.dto.FamilyDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FamilyDao {

    // 피고용인 가족정보조회 - 항목 전체 리스트
    Page<FamilyDto> getFamilyList(EmpSearchDto params) throws Exception;
}
