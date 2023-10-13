package kr.fingate.gs.cmsn.prdct.dao;

import kr.fingate.gs.cmsn.prdct.dto.*;
import kr.fingate.gs.cmsn.vo.prdct.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PrdctModDao {

    // 상품율관리
    void insPrdctRateMngmn(PrdctRateMngmnVO params);
    void delPrdctRateMngmn(PrdctRateMngmnVO params);

    // 상품평가계수
    void insPrdctEvltnFigure(List<PrdctEvltnFigureDto> params);
    void delPrdctEvltnFigure(PrdctEvltnFigureVO params);

    // 평가업적산출기준
    void insPrdctEvltnStd(List<PrdctEvltnStdDto> params);
    void delPrdctEvltnStd(PrdctEvltnStdVO params);

    // 지급율 조정계수
    void insPrdctPymntFigure(PrdctPymntFigureVO params);
    void delPrdctPymntFigure(PrdctPymntFigureVO params);

    // 수수료유형별조정율
    void insPrdctExtpyAdjst(List<PrdctExtpyAdjstDto> params);
    void delPrdctExtpyAdjst(PrdctExtpyAdjstVO params);

    // 납기수수료유형별조정율
    void insPrdctPeriodExtpy(List<PrdctPeriodExtpyDto> params);
    void delPrdctPeriodExtpy(PrdctPeriodExtpyVO params);

    // 지급액공시
    void insPrdctPymntAlarm(List<PrdctPymntAlarmDto> params);
    void delPrdctPymntAlarm(PrdctPymntAlarmVO params);

    // 보험사환산
    void insPrdctIrch(PrdctIrchVO params);
    void delPrdctIrch(PrdctIrchVO params);

    // 보험사조정율
    void insPrdctInsrrAdjst(PrdctInsrrAdjstVO params);
    void updPrdctInsrrAdjst(PrdctInsrrAdjstVO params);
    void delPrdctInsrrAdjst(PrdctInsrrAdjstVO params);

}
