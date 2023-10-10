package kr.fingate.gs.prdctm.prdct.dao;

import com.github.pagehelper.Page;
import kr.fingate.gs.prdctm.prdct.dto.PrdctInfoDto;
import kr.fingate.gs.prdctm.prdct.dto.RprsnPrdctDto;
import kr.fingate.gs.prdctm.prdct.dto.SearchPrdctDto;
import kr.fingate.gs.prdctm.vo.RprsnPrdctVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PrdctDao {
    
    // 상품정보 리스트 조회
    Page<PrdctInfoDto> getPrdctInfoList(SearchPrdctDto params) throws Exception;
    
    // 대표상품 리스트 조회
    Page<RprsnPrdctDto> getRprsnPrdctList(SearchPrdctDto params) throws Exception;
    
    // 대표상품 저장
    void insRprsnPrdt(RprsnPrdctVO params) throws Exception;
    
    // 대표상품 수정
    void updRprsnPrdt(RprsnPrdctVO params) throws Exception;


}
