package kr.fingate.gs.cmsn.prdct.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import kr.fingate.gs.cmsn.prdct.dao.PrdctDao;
import kr.fingate.gs.cmsn.prdct.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrdctService {

    final PrdctDao prdctDao;

    /**
     * 대표상품 리스트
     * @param searchRprsnPrdctDto
     * @return
     * @throws Exception
     */
    public Page<RprsnPrdctDto> getRprsnPrdctList(SearchRprsnPrdctDto searchRprsnPrdctDto) throws Exception {
        PageHelper.startPage(searchRprsnPrdctDto.getPageNum(), searchRprsnPrdctDto.getPageSize());
        return prdctDao.getRprsnPrdctList(searchRprsnPrdctDto);
    }

    /**
     * 상품율 리스트
     * @param searchPrdctRateMngmnDto
     * @return
     * @throws Exception
     */
    public List<PrdctRateMngmnDto> getPrdctRateMngmnList(SearchPrdctRateMngmnDto searchPrdctRateMngmnDto)  throws Exception {
        return prdctDao.getPrdctRateMngmnList(searchPrdctRateMngmnDto);
    }

    /**
     * 상품율관리 상세 저장
     * @param searchPrdctRateSeqDto
     * @return
     * @throws Exception
     */
    public PrdtRateDtalDto getPrdctRateDtails(@RequestBody SearchPrdctRateSeqDto searchPrdctRateSeqDto)  throws Exception {
        PrdtRateDtalDto prdtRateDtalDto = new PrdtRateDtalDto();

        prdtRateDtalDto.setPrdctEvltnFigureList(this.getPrdctEvltnFigureList(searchPrdctRateSeqDto));
        prdtRateDtalDto.setPrdctEvltnStdList(this.getPrdctEvltnStdList(searchPrdctRateSeqDto));
        prdtRateDtalDto.setPrdctExtpyAdjstList(this.getPrdctExtpyAdjstList(searchPrdctRateSeqDto));
        prdtRateDtalDto.setPrdctIrchList(this.getPrdctIrchList(searchPrdctRateSeqDto));
        prdtRateDtalDto.setPrdctPeriodExtpyList(this.getPrdctPeriodExtpyList(searchPrdctRateSeqDto));
        prdtRateDtalDto.setPrdctPymntAlarmList(this.getPrdctPymntAlarmList(searchPrdctRateSeqDto));
        prdtRateDtalDto.setPrdctPymntFigureList(this.getPrdctPymntFigureList(searchPrdctRateSeqDto));

        return prdtRateDtalDto;
    }


    /**
     * 평가계수
     * @param searchPrdctRateSeqDto
     * @return
     * @throws Exception
     */
    private List<PrdctEvltnFigureDto> getPrdctEvltnFigureList(SearchPrdctRateSeqDto searchPrdctRateSeqDto)  throws Exception {
        return prdctDao.getPrdctEvltnFigureList(searchPrdctRateSeqDto);
    }

    /**
     * 평가업적산출기준
     * @param searchPrdctRateSeqDto
     * @return
     * @throws Exception
     */
    private List<PrdctEvltnStdDto> getPrdctEvltnStdList(SearchPrdctRateSeqDto searchPrdctRateSeqDto)  throws Exception {
        return prdctDao.getPrdctEvltnStdList(searchPrdctRateSeqDto);
    }


    /**
     * 수당유형별 조정율
     * @param searchPrdctRateSeqDto
     * @return
     * @throws Exception
     */
    private List<PrdctExtpyAdjstDto> getPrdctExtpyAdjstList(SearchPrdctRateSeqDto searchPrdctRateSeqDto)  throws Exception {
        return prdctDao.getPrdctExtpyAdjstList(searchPrdctRateSeqDto);
    }

    /**
     * 보험사환산
     * @param searchPrdctRateSeqDto
     * @return
     * @throws Exception
     */
    private List<PrdctIrchDto> getPrdctIrchList(SearchPrdctRateSeqDto searchPrdctRateSeqDto)  throws Exception {
        return prdctDao.getPrdctIrchList(searchPrdctRateSeqDto);
    }

    /**
     * 납기수수료유형별 조정율
     * @param searchPrdctRateSeqDto
     * @return
     * @throws Exception
     */
    private List<PrdctPeriodExtpyDto> getPrdctPeriodExtpyList(SearchPrdctRateSeqDto searchPrdctRateSeqDto)  throws Exception {
        return prdctDao.getPrdctPeriodExtpyList(searchPrdctRateSeqDto);
    }

    /**
     * 지급액공시
     * @param searchPrdctRateSeqDto
     * @return
     * @throws Exception
     */
    private List<PrdctPymntAlarmDto> getPrdctPymntAlarmList(SearchPrdctRateSeqDto searchPrdctRateSeqDto)  throws Exception {
        return prdctDao.getPrdctPymntAlarmList(searchPrdctRateSeqDto);
    }

    /**
     * 상품조정율
     * @param searchPrdctRateSeqDto
     * @return
     * @throws Exception
     */
    private List<PrdctPymntFigureDto> getPrdctPymntFigureList(SearchPrdctRateSeqDto searchPrdctRateSeqDto)  throws Exception {
        return prdctDao.getPrdctPymntFigureList(searchPrdctRateSeqDto);
    }
    
}
