package kr.fingate.gs.prdctm.rprsn.dao;

import com.github.pagehelper.Page;
import kr.fingate.gs.prdctm.rprsn.dto.RprsnPrdctDto;
import kr.fingate.gs.prdctm.rprsn.dto.SearchRprsnPrdctDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RprsnPrdctDao {
    
    // 대표상품 리스트 조회
    Page<RprsnPrdctDto> getRprsnPrdctList(SearchRprsnPrdctDto params) throws Exception;

    RprsnPrdctDto getRprsnPrdct(SearchRprsnPrdctDto params) throws Exception;


}
