package kr.fingate.gs.sales.cntrc.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import kr.fingate.gs.core.aop.exception.BizException;
import kr.fingate.gs.core.aop.response.ResponseCode;
import kr.fingate.gs.sales.cntrc.dao.CntrcDao;
import kr.fingate.gs.sales.cntrc.dao.CntrcModDao;
import kr.fingate.gs.sales.cntrc.dto.CntrcListDto;
import kr.fingate.gs.sales.cntrc.dto.CntrcListSearchDto;
import kr.fingate.gs.sales.cntrc.dto.SbscrListDto;
import kr.fingate.gs.sales.cntrc.dto.SbscrListSearchDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CntrcService {

    final CntrcDao cntrcDao;

    final CntrcModDao cntrcModDao;

    public Page<SbscrListDto> getSbscrList(SbscrListSearchDto sbscrListSearchDto) {
        Page<SbscrListDto> roleList = new Page<>();

        try {

            sbscrListSearchDto.setClientNo(100000);
            PageHelper.startPage(sbscrListSearchDto.getPageNum(), sbscrListSearchDto.getPageSize());
            //검색어 암호화를 위해 암호화 전용 속성 set
            sbscrListSearchDto.setQuickSearchValueEncrypt(sbscrListSearchDto.getQuickSearchValue());
            roleList = cntrcDao.getSbscrList(sbscrListSearchDto);

        } catch (BizException e) {
            log.error("CntrcService.getSbscrList BizException : {}", e.getMessage());
            throw new BizException(e.getMessage());
        } catch (Exception e) {
            log.error("CntrcService.getSbscrList Exception : {}", e.getMessage(), e);
            throw new BizException(ResponseCode.INTERNAL_SERVER_ERROR, e);
        }

        return roleList;
    }

    public Page<CntrcListDto> getCntrcList(CntrcListSearchDto cntrcListSearchDto) {
        Page<CntrcListDto> roleList = new Page<>();

        try {

            cntrcListSearchDto.setClientNo(100000);
            PageHelper.startPage(cntrcListSearchDto.getPageNum(), cntrcListSearchDto.getPageSize());
            //검색어 암호화를 위해 암호화 전용 속성 set
            cntrcListSearchDto.setQuickSearchValueEncrypt(cntrcListSearchDto.getQuickSearchValue());
            roleList = cntrcDao.getCntrcList(cntrcListSearchDto);

        } catch (BizException e) {
            log.error("CntrcService.getCntrcList BizException : {}", e.getMessage());
            throw new BizException(e.getMessage());
        } catch (Exception e) {
            log.error("CntrcService.getCntrcList Exception : {}", e.getMessage(), e);
            throw new BizException(ResponseCode.INTERNAL_SERVER_ERROR, e);
        }

        return roleList;
    }

}
