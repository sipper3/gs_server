package kr.fingate.gs.prdctm.rprsn.dao;

import kr.fingate.gs.prdctm.vo.RprsnPrdctVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RprsnPrdctModDao {
    
    // 대표상품 저장
    void insRprsnPrdct(RprsnPrdctVO params) throws Exception;
    
    // 대표상품 수정
    void updRprsnPrdct(RprsnPrdctVO params) throws Exception;

    // 대표상품 삭제
    void delRprsnPrdct(RprsnPrdctVO params) throws Exception;




}
