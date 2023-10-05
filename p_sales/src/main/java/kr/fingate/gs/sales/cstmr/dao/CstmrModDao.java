package kr.fingate.gs.sales.cstmr.dao;

import kr.fingate.gs.sales.vo.CstmrAdtnInfoVO;
import kr.fingate.gs.sales.vo.CstmrMemoVO;
import kr.fingate.gs.sales.vo.CstmrSmsAuthVO;
import kr.fingate.gs.sales.vo.CstmrVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CstmrModDao {

    void insCstmr(CstmrVO cstmrVO);

    int updCstmr(CstmrVO cstmrVO);

    int delCstmr(CstmrVO cstmrVO);

    void insCstmrAdtnInfo(CstmrAdtnInfoVO cstmrAdtnInfoVO);

    int updCstmrAdtnInfo(CstmrAdtnInfoVO cstmrAdtnInfoVO);

    void insCstmrMemo(CstmrMemoVO cstmrMemoVO);

    void insCstmrSmsAuth(CstmrSmsAuthVO cstmrSmsAuthVO);

    int updCstmrSmsAuth(CstmrSmsAuthVO cstmrSmsAuthVO);

    void updCstmrClphnNo(CstmrVO cstmrVO);
}
