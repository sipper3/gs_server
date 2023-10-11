package kr.fingate.gs.prdctm.prdct.dao;

import kr.fingate.gs.prdctm.vo.RprsnPrdctVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PrdctModDao {
    
    // 상품 저장
    void insPrdt(RprsnPrdctVO params) throws Exception;
    
    // 상품 수정
    void updPrdt(RprsnPrdctVO params) throws Exception;

    // 상품 삭제
    void delPrdt(RprsnPrdctVO params) throws Exception;




}
