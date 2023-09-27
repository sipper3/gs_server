package kr.fingate.gs.sales.cstmr.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import kr.fingate.gs.comon.exception.BizError;
import kr.fingate.gs.comon.exception.BizException;
import kr.fingate.gs.sales.cstmr.dao.CstmrDao;
import kr.fingate.gs.sales.cstmr.dto.CstmrListDto;
import kr.fingate.gs.sales.cstmr.dto.CstmrListSearchDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CstmrService {

    final CstmrDao cstmrDao;


    public Page<CstmrListDto> getCstmrList(CstmrListSearchDto cstmrListSearchDto) {
        Page<CstmrListDto> roleList = new Page<>();

        try {
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
}
