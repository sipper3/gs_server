package kr.fingate.gs.prdctm.prdct.dao;

import com.github.pagehelper.Page;
import kr.fingate.gs.prdctm.prdct.dto.PrdctInfoDto;
import kr.fingate.gs.prdctm.prdct.dto.SearchPrdctDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PrdctDao {
    
    // 상품정보 리스트 조회
    Page<PrdctInfoDto> getPrdctInfoList(SearchPrdctDto params) throws Exception;

    PrdctInfoDto getPrdctInfo(SearchPrdctDto params) throws Exception;


}
