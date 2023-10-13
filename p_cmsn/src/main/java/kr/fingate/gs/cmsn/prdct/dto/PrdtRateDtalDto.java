package kr.fingate.gs.cmsn.prdct.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PrdtRateDtalDto {

    // 상품율관리
    private PrdctRateMngmnDto prdctRateMngmn;

    // 평가계수
    private List<PrdctEvltnFigureDto> prdctEvltnFigureList;

    // 평가업적산출기준
    private List<PrdctEvltnStdDto> prdctEvltnStdList;

    // 수당유형별 조정율
    private List<PrdctExtpyAdjstDto> prdctExtpyAdjstList;

    // 보험사환산
    private List<PrdctIrchDto> prdctIrchList;

    // 납기수수료유형별 조정율
    private List<PrdctPeriodExtpyDto> prdctPeriodExtpyList;

    // 지급액공시
    private List<PrdctPymntAlarmDto> prdctPymntAlarmList;

    // 상품조정율
    private List<PrdctPymntFigureDto> prdctPymntFigureList;

}
