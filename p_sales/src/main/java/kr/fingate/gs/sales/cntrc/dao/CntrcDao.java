package kr.fingate.gs.sales.cntrc.dao;

import com.github.pagehelper.Page;
import kr.fingate.gs.common.annotation.PrivateSql;
import kr.fingate.gs.sales.cntrc.dto.CntrcListDto;
import kr.fingate.gs.sales.cntrc.dto.CntrcListSearchDto;
import kr.fingate.gs.sales.cntrc.dto.SbscrListDto;
import kr.fingate.gs.sales.cntrc.dto.SbscrListSearchDto;
import kr.fingate.gs.sales.cstmr.dto.*;
import kr.fingate.gs.sales.vo.CstmrSmsAuthVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CntrcDao {
    @PrivateSql
    Page<SbscrListDto> getSbscrList(SbscrListSearchDto sbscrListSearchDto);
    @PrivateSql
    Page<CntrcListDto> getCntrcList(CntrcListSearchDto cntrcListSearchDto);




}
