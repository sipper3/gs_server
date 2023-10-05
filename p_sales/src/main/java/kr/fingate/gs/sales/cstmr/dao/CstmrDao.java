package kr.fingate.gs.sales.cstmr.dao;

import com.github.pagehelper.Page;
import kr.fingate.gs.sales.cstmr.dto.*;
import kr.fingate.gs.sales.vo.CstmrSmsAuthVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CstmrDao {
    Page<CstmrListDto> getCstmrList(CstmrListSearchDto cstmrListSearchDto);

    CstmrListDto getCstmr(CstmrSearchDto cstmrSearchDto);

    DupCstmrDto getDuplicateCstmr(DupCstmrSearchDto dupCstmrSearchDto);

    CstmrMemoListDto getCstmrMemoList(CstmrSearchDto cstmrSearchDto);

    String getSmsAuthNo();

    CstmrSmsAuthVO getCstmrSmsAuth(CstmrSmsAuthVO cstmrSmsAuthVO);

    List<CstmrRspnsChangeDto> getCstmrRspnsChangeList(CstmrRspnsChangeSearchDto cstmrRspnsChangeSearchDto);

    CstmrRspnsChangeDto getCstmrRspnsChange(CstmrRspnsChangeSearchDto cstmrRspnsChangeSearchDto);


}
