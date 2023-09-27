package kr.fingate.gs.sales.cstmr.dao;

import com.github.pagehelper.Page;
import kr.fingate.gs.sales.cstmr.dto.CstmrListDto;
import kr.fingate.gs.sales.cstmr.dto.CstmrListSearchDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CstmrDao {
    Page<CstmrListDto> getCstmrList(CstmrListSearchDto cstmrListSearchDto);
}
