package kr.fingate.gs.sales.cstmr.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import kr.fingate.gs.common.exception.BizError;
import kr.fingate.gs.common.exception.BizException;
import kr.fingate.gs.sales.cstmr.dao.CstmrDao;
import kr.fingate.gs.sales.cstmr.dao.CstmrModDao;
import kr.fingate.gs.sales.cstmr.dto.CstmrListDto;
import kr.fingate.gs.sales.cstmr.dto.CstmrListSearchDto;
import kr.fingate.gs.sales.vo.CstmrVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CstmrService {

    final CstmrDao cstmrDao;

    final CstmrModDao cstmrModDao;



    public Page<CstmrListDto> getCstmrList(CstmrListSearchDto cstmrListSearchDto) {
        Page<CstmrListDto> roleList = new Page<>();

        try {

            cstmrListSearchDto.setClientNo(100000);
            cstmrListSearchDto.setRspnsUserNo(1);
            PageHelper.startPage(cstmrListSearchDto.getPageNum(), cstmrListSearchDto.getPageSize());
            roleList = cstmrDao.getCstmrList(cstmrListSearchDto);

        } catch (BizException e) {
            log.error("CstmrService.getCstmrList BizException : {}", e.getMessage());
            throw new BizException(e.getMessage());
        } catch (Exception e) {
            log.error("CstmrService.getCstmrList Exception : {}", e.getMessage(), e);
            throw new BizException(BizError.INTERNAL_SERVER_ERROR, e);
        }

        return roleList;
    }
    public long insCstmr(CstmrVO cstmrVO) {
        CstmrVO cstmrVo = CstmrVO.builder()
                        .cstmrName("테스트1")
                        .clphnNo("01012345678")
                        .brthd("20010105")
                        .prvcyUseAgrmnYn("N")
                        .prvcyUseAgrmnType("Z")
                        .mrktnRcvAgrmnYn("N")
                        .mrktnRcvAgrmnType("Z")
                        .tlphnRcvAgrmnYn("N")
                        .smsRcvAgrmnYn("N")
                        .emailRcvAgrmnYn("N")
                .build();

        cstmrVo.setRegUserNo(1);
        cstmrVo.setModUserNo(1);
        cstmrVo.setClientNo(100000);

        cstmrModDao.insCstmr(cstmrVo);
        return cstmrVo.getCstmrNo();
    }
}
