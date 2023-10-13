package kr.fingate.gs.cmsn.prdct.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import kr.fingate.gs.cmsn.prdct.dao.PrdctDao;
import kr.fingate.gs.cmsn.prdct.dao.PrdctModDao;
import kr.fingate.gs.cmsn.prdct.dto.*;
import kr.fingate.gs.cmsn.vo.prdct.*;
import kr.fingate.gs.common.dto.code.CodeDto;
import kr.fingate.gs.common.external.excel.ExcelExportModel;
import kr.fingate.gs.common.external.excel.ExcelHeader;
import kr.fingate.gs.common.external.excel.event.util.ExcelUtils;
import kr.fingate.gs.common.util.DateUtil;
import kr.fingate.gs.core.security.dto.UserTokenDto;
import kr.fingate.gs.core.service.CoreService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.util.CellReference;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PrdctService {

    final PrdctDao prdctDao;
    final PrdctModDao prdctModDao;

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
    public PrdtRateDtalDto getPrdctRateDtails(SearchPrdctRateSeqDto searchPrdctRateSeqDto)  throws Exception {
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

    /**
     * 상품율관리 상세 저장
     * @param prdtRateDtalDto
     * @return
     * @throws Exception
     */
    public PrdctRateMngmnDto savePrdctRateDtails(PrdtRateDtalDto prdtRateDtalDto)  throws Exception {

        PrdctRateMngmnDto prdctRateMngmnDto = prdtRateDtalDto.getPrdctRateMngmn();

        UserTokenDto userTokenDto = CoreService.getUserInfo();
        prdctRateMngmnDto.setClientNo(userTokenDto.getClientNo());
        prdctRateMngmnDto.setRegUserNo(userTokenDto.getUserNo());

        if(prdctRateMngmnDto.getPrdctRateSeq() == 0) {
            // 신규 저장
            SearchPrdctRateSeqDto searchPrdctRateSeqDto = new SearchPrdctRateSeqDto();
            searchPrdctRateSeqDto.setClientNo(prdctRateMngmnDto.getClientNo());
            searchPrdctRateSeqDto.setRprsnPrdctCode(prdctRateMngmnDto.getRprsnPrdctCode());
            prdctRateMngmnDto.setPrdctRateSeq(prdctDao.getMaxPrdctRateSeq(searchPrdctRateSeqDto));
        } else {
            this.delPrdctRateDtails(prdtRateDtalDto.getPrdctRateMngmn());
        }

        this.insPrdctRateDtails(prdtRateDtalDto);

        return prdctRateMngmnDto;
    }

    private void insPrdctRateDtails(PrdtRateDtalDto prdtRateDtalDto)  throws Exception {

        PrdctRateMngmnDto prdctRateMngmnDto = prdtRateDtalDto.getPrdctRateMngmn();

        // 상품율관리 저장
        prdctModDao.insPrdctRateMngmn(prdctRateMngmnDto);

        // 평가계수 등록
        if(!prdtRateDtalDto.getPrdctEvltnFigureList().isEmpty()) {
            for (PrdctEvltnFigureDto prdctEvltnFigureDto : prdtRateDtalDto.getPrdctEvltnFigureList()) {
                prdctEvltnFigureDto.setRprsnPrdctCode(prdctRateMngmnDto.getRprsnPrdctCode());
                prdctEvltnFigureDto.setPrdctRateSeq(prdctRateMngmnDto.getPrdctRateSeq());
            }
            prdctModDao.insPrdctEvltnFigure(prdtRateDtalDto.getPrdctEvltnFigureList());
        }

        // 평가업적산출기준 등록
        if(!prdtRateDtalDto.getPrdctEvltnStdList().isEmpty()) {
            for(PrdctEvltnStdDto prdctEvltnStdDto : prdtRateDtalDto.getPrdctEvltnStdList()) {
                prdctEvltnStdDto.setRprsnPrdctCode(prdctRateMngmnDto.getRprsnPrdctCode());
                prdctEvltnStdDto.setPrdctRateSeq(prdctRateMngmnDto.getPrdctRateSeq());
            }
            prdctModDao.insPrdctEvltnStd(prdtRateDtalDto.getPrdctEvltnStdList());
        }

        // 수당유형별 조정율 등록
        if(!prdtRateDtalDto.getPrdctExtpyAdjstList().isEmpty()) {
            for(PrdctExtpyAdjstDto prdctExtpyAdjstDto: prdtRateDtalDto.getPrdctExtpyAdjstList()) {
                prdctExtpyAdjstDto.setRprsnPrdctCode(prdctRateMngmnDto.getRprsnPrdctCode());
                prdctExtpyAdjstDto.setPrdctRateSeq(prdctRateMngmnDto.getPrdctRateSeq());
            }
            prdctModDao.insPrdctExtpyAdjst(prdtRateDtalDto.getPrdctExtpyAdjstList());
        }

        // 보험사환산 등록 (1보다 크거나 1일경우 환산율1차년이 입력되어있을 경우만 등록)
        if(!prdtRateDtalDto.getPrdctIrchList().isEmpty()) {
            for(PrdctIrchDto prdctIrchDto: prdtRateDtalDto.getPrdctIrchList()) {
                prdctIrchDto.setRprsnPrdctCode(prdctRateMngmnDto.getRprsnPrdctCode());
                prdctIrchDto.setPrdctRateSeq(prdctRateMngmnDto.getPrdctRateSeq());
                prdctModDao.insPrdctIrch(prdctIrchDto);
            }
        }

        // 납기수수료 유형별 조정율 등록
        if(!prdtRateDtalDto.getPrdctPeriodExtpyList().isEmpty()) {
            for (PrdctPeriodExtpyDto prdctPeriodExtpyDto : prdtRateDtalDto.getPrdctPeriodExtpyList()) {
                prdctPeriodExtpyDto.setRprsnPrdctCode(prdctRateMngmnDto.getRprsnPrdctCode());
                prdctPeriodExtpyDto.setPrdctRateSeq(prdctRateMngmnDto.getPrdctRateSeq());
            }
            prdctModDao.insPrdctPeriodExtpy(prdtRateDtalDto.getPrdctPeriodExtpyList());
        }

        // 지급액공시 등록
        if(!prdtRateDtalDto.getPrdctPymntAlarmList().isEmpty()) {
            for (PrdctPymntAlarmDto prdctPymntAlarmDto : prdtRateDtalDto.getPrdctPymntAlarmList()) {
                prdctPymntAlarmDto.setRegUserNo(prdctRateMngmnDto.getRegUserNo());
                prdctPymntAlarmDto.setRprsnPrdctCode(prdctRateMngmnDto.getRprsnPrdctCode());
                prdctPymntAlarmDto.setPrdctRateSeq(prdctRateMngmnDto.getPrdctRateSeq());
            }

            prdctModDao.insPrdctPymntAlarm(prdtRateDtalDto.getPrdctPymntAlarmList());

        }

        // 지급율 조정계수 등록
        if(!prdtRateDtalDto.getPrdctPymntFigureList().isEmpty()) {
            for(PrdctPymntFigureDto prdctPymntFigureDto: prdtRateDtalDto.getPrdctPymntFigureList()) {
                prdctPymntFigureDto.setRprsnPrdctCode(prdctRateMngmnDto.getRprsnPrdctCode());
                prdctPymntFigureDto.setPrdctRateSeq(prdctRateMngmnDto.getPrdctRateSeq());
                prdctModDao.insPrdctPymntFigure(prdctPymntFigureDto);
            }
        }
    }

    private void delPrdctRateDtails(PrdctRateMngmnDto prdctRateMngmnDto)  throws Exception {

        // 평가계수 삭제
        PrdctEvltnFigureVO prdctEvltnFigureVO = new PrdctEvltnFigureVO();
        prdctEvltnFigureVO.setRprsnPrdctCode(prdctRateMngmnDto.getRprsnPrdctCode());
        prdctEvltnFigureVO.setPrdctRateSeq(prdctRateMngmnDto.getPrdctRateSeq());
        prdctModDao.delPrdctEvltnFigure(prdctEvltnFigureVO);

        // 평가업적산출기준 삭제
        PrdctEvltnStdVO prdctEvltnStdVO = new PrdctEvltnStdVO();
        prdctEvltnStdVO.setRprsnPrdctCode(prdctRateMngmnDto.getRprsnPrdctCode());
        prdctEvltnStdVO.setPrdctRateSeq(prdctRateMngmnDto.getPrdctRateSeq());
        prdctModDao.delPrdctEvltnStd(prdctEvltnStdVO);

        // 수당유형별 조정율 삭제
        PrdctExtpyAdjstVO prdctExtpyAdjstVO = new PrdctExtpyAdjstVO();
        prdctExtpyAdjstVO.setRprsnPrdctCode(prdctRateMngmnDto.getRprsnPrdctCode());
        prdctExtpyAdjstVO.setPrdctRateSeq(prdctRateMngmnDto.getPrdctRateSeq());
        prdctModDao.delPrdctExtpyAdjst(prdctExtpyAdjstVO);

        // 보험사환산 삭제
        PrdctIrchVO prdctIrchVO = new PrdctIrchVO();
        prdctIrchVO.setRprsnPrdctCode(prdctRateMngmnDto.getRprsnPrdctCode());
        prdctIrchVO.setPrdctRateSeq(prdctRateMngmnDto.getPrdctRateSeq());
        prdctModDao.delPrdctIrch(prdctIrchVO);

        // 납기수수료 유형별 조정율 삭제
        PrdctPeriodExtpyVO prdctPeriodExtpyVO = new PrdctPeriodExtpyVO();
        prdctPeriodExtpyVO.setRprsnPrdctCode(prdctRateMngmnDto.getRprsnPrdctCode());
        prdctPeriodExtpyVO.setPrdctRateSeq(prdctRateMngmnDto.getPrdctRateSeq());
        prdctModDao.delPrdctPeriodExtpy(prdctPeriodExtpyVO);

        // 지급액공시 삭제
        PrdctPymntAlarmVO prdctPymntAlarmVO = new PrdctPymntAlarmVO();
        prdctPymntAlarmVO.setRprsnPrdctCode(prdctRateMngmnDto.getRprsnPrdctCode());
        prdctPymntAlarmVO.setPrdctRateSeq(prdctRateMngmnDto.getPrdctRateSeq());
        prdctModDao.delPrdctPymntAlarm(prdctPymntAlarmVO);

        // 지급율 조정계수 삭제
        PrdctPymntFigureVO prdctPymntFigureVO = new PrdctPymntFigureVO();
        prdctPymntFigureVO.setRprsnPrdctCode(prdctRateMngmnDto.getRprsnPrdctCode());
        prdctPymntFigureVO.setPrdctRateSeq(prdctRateMngmnDto.getPrdctRateSeq());
        prdctModDao.delPrdctPymntFigure(prdctPymntFigureVO);
    }

    /**
     * 상품율관리 확정 종료일
     * @param prdctRateMngmnDto
     * @return
     * @throws Exception
     */
    public LastDfntnAplctEndDateDto getLastDfntnAplctEndDate(PrdctRateMngmnDto prdctRateMngmnDto)  throws Exception {
        return prdctDao.getLastDfntnAplctEndDate(prdctRateMngmnDto);
    }

    /**
     * 상품율관리 삭제
     * @param prdctRateMngmnDto
     * @throws Exception
     */
    public void delPrdctRate(@RequestBody PrdctRateMngmnDto prdctRateMngmnDto)  throws Exception {

        UserTokenDto userTokenDto = CoreService.getUserInfo();
        prdctRateMngmnDto.setClientNo(userTokenDto.getClientNo());

        prdctModDao.delPrdctRateMngmn(prdctRateMngmnDto);
        this.delPrdctRateDtails(prdctRateMngmnDto);
    }

    /**
     * 상품율 업로드 양식 다운로드
     */
    public ModelAndView downPrdctRateTmpl()  throws Exception {
        ExcelHeader header = new ExcelHeader();
        ExcelExportModel<?> xxm = new ExcelExportModel<>();
        ModelAndView mv = new ModelAndView("xlsxView");

        int rowNo = 1;
        int colsIdx = 1;
        xxm.setSheetName("FP_SAMPLE");

        /* TITLE ROW 생성*/
        String commentCol = CellReference.convertNumToColString(colsIdx);
        header.addTopComment(rowNo++, commentCol, "상품율(업로드양식)");
        header.addTopComment(rowNo++, commentCol, "- \"적용종료일≠20991231\"경우 \"상품율\"에서 자동종료");
        header.addTopComment(rowNo++, commentCol, "- \"적용시작일\"이 기존시작일과 상이할 경우 기존시작일 자동종료 후 반영");
        header.addTopComment(rowNo++, commentCol, "- 확정여부 'Y'로 업로드시 기존 등록된 상품율은 자동 종료처리됨.");

        /* 1번째 ROW생성 */
        header.addDoubleHeader(rowNo, CellReference.convertNumToColString(colsIdx) , "보험사▼" , 3, CellReference.convertNumToColString(colsIdx++), 1, "center", "INSRR");
        header.addDoubleHeader(rowNo, CellReference.convertNumToColString(colsIdx) , "대표상품코드" , 3, CellReference.convertNumToColString(colsIdx++), 1);
        header.addDoubleHeader(rowNo, CellReference.convertNumToColString(colsIdx) , "상품명" , 3, CellReference.convertNumToColString(colsIdx++), 1);
        header.addDoubleHeader(rowNo, CellReference.convertNumToColString(colsIdx) , "예시기준" , 3, CellReference.convertNumToColString(colsIdx++), 1);
        header.addDoubleHeader(rowNo, CellReference.convertNumToColString(colsIdx) , "납입기간" , 3, CellReference.convertNumToColString(colsIdx++), 1);

        int startIdx = colsIdx;
        header.addDoubleHeader(rowNo, CellReference.convertNumToColString(colsIdx) , "환산율" , 2, CellReference.convertNumToColString(colsIdx), 3);
        colsIdx += 3;
        header.addDoubleHeader(rowNo, CellReference.convertNumToColString(colsIdx) , "평가계수" , 2, CellReference.convertNumToColString(colsIdx), 4);
        colsIdx += 4;
        header.addDoubleHeader(rowNo, CellReference.convertNumToColString(colsIdx) , "지급율" , 2, CellReference.convertNumToColString(colsIdx), 4);
        colsIdx += 4;

        header.addDoubleHeader(rowNo, CellReference.convertNumToColString(colsIdx) , "일시납성적적용" , 3, CellReference.convertNumToColString(colsIdx++), 1);
        header.addDoubleHeader(rowNo, CellReference.convertNumToColString(colsIdx) , "OA홀딩율" , 3, CellReference.convertNumToColString(colsIdx++), 1);
        header.addDoubleHeader(rowNo, CellReference.convertNumToColString(colsIdx) , "지급조정율" , 3, CellReference.convertNumToColString(colsIdx++), 1);
        header.addDoubleHeader(rowNo, CellReference.convertNumToColString(colsIdx) , "지급조정공시율" , 3 , CellReference.convertNumToColString(colsIdx++), 1);
        header.addDoubleHeader(rowNo, CellReference.convertNumToColString(colsIdx) , "적용시작일" , 3 , CellReference.convertNumToColString(colsIdx++), 1);
        header.addDoubleHeader(rowNo, CellReference.convertNumToColString(colsIdx) , "적용종료일" , 3 , CellReference.convertNumToColString(colsIdx++), 1);
        header.addDoubleHeader(rowNo, CellReference.convertNumToColString(colsIdx) , "확정여부" , 3 , CellReference.convertNumToColString(colsIdx++), 1);
        header.addDoubleHeader(rowNo, CellReference.convertNumToColString(colsIdx) , "노출(미사용)" , 3 , CellReference.convertNumToColString(colsIdx++), 1);
        header.addDoubleHeader(rowNo, CellReference.convertNumToColString(colsIdx) , "비고" , 3 , CellReference.convertNumToColString(colsIdx++), 1);
        header.addDoubleHeader(rowNo, CellReference.convertNumToColString(colsIdx) , "순번" , 3 , CellReference.convertNumToColString(colsIdx++), 1);

        /* 3번째 Header ROW생성 */
        colsIdx = startIdx;
        header.addDoubleHeader(rowNo+2, CellReference.convertNumToColString(colsIdx) , "1차년" , 1, CellReference.convertNumToColString(colsIdx++), 1);
        header.addDoubleHeader(rowNo+2, CellReference.convertNumToColString(colsIdx) , "2차년" , 1, CellReference.convertNumToColString(colsIdx++), 1);
        header.addDoubleHeader(rowNo+2, CellReference.convertNumToColString(colsIdx) , "3차년" , 1, CellReference.convertNumToColString(colsIdx++), 1);
        header.addDoubleHeader(rowNo+2, CellReference.convertNumToColString(colsIdx) , "1차년" , 1, CellReference.convertNumToColString(colsIdx++), 1);
        header.addDoubleHeader(rowNo+2, CellReference.convertNumToColString(colsIdx) , "2차년" , 1, CellReference.convertNumToColString(colsIdx++), 1);
        header.addDoubleHeader(rowNo+2, CellReference.convertNumToColString(colsIdx) , "3차년" , 1, CellReference.convertNumToColString(colsIdx++), 1);
        header.addDoubleHeader(rowNo+2, CellReference.convertNumToColString(colsIdx) , "일시납" , 1, CellReference.convertNumToColString(colsIdx++), 1);
        header.addDoubleHeader(rowNo+2, CellReference.convertNumToColString(colsIdx) , "1차년" , 1, CellReference.convertNumToColString(colsIdx++), 1);
        header.addDoubleHeader(rowNo+2, CellReference.convertNumToColString(colsIdx) , "2차년" , 1, CellReference.convertNumToColString(colsIdx++), 1);
        header.addDoubleHeader(rowNo+2, CellReference.convertNumToColString(colsIdx) , "3차년" , 1, CellReference.convertNumToColString(colsIdx++), 1);
        header.addDoubleHeader(rowNo+2, CellReference.convertNumToColString(colsIdx) , "일시납" , 1, CellReference.convertNumToColString(colsIdx++), 1);

        Map<String,String[]> codes = new HashMap<String,String[]>();
        CodeDto codeDto = new CodeDto();
        codeDto.setUseYn("Y");
        codeDto.setCodeInit("INSRR");

        // TODO 코드 조회
        List<CodeDto> insrCodeList = null;
//        List<CodeDto> insrCodeList = codeService.selCode(codeModel);

        String[] codeInsr = new String[insrCodeList.size()];
        for (int i = 0; i < insrCodeList.size(); i++) {
            codeInsr[i] = insrCodeList.get(i).getCodeName();
        }

        codes.put("INSRR", codeInsr);
        mv.addObject("fileName", "양식_상품율업로드RP(HY포함)_" + DateUtil.getToday());


        xxm.setHeader(header);
        xxm.setFixRow(1);
        mv.addObject("workbook", ExcelUtils.getWorkbook(xxm, codes, getClass()));

        mv.addObject("sheet", xxm);
        return mv;
    }
}
