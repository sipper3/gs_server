package kr.fingate.gs.cmsn.prdct.dao;

import kr.fingate.gs.cmsn.vo.prdct.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PrdctModDao {

    // 상품율관리
    void insPrdctRateMngmn(PrdctRateMngmnVO prdctRateMngmnVO);
    void delPrdctRateMngmn(PrdctRateMngmnVO prdctRateMngmnVO);

    // 상품평가계수
    void insPrdctEvltnFigure(PrdctEvltnFigureVO prdctEvltnFigureVO);
    void delPrdctEvltnFigure(PrdctEvltnFigureVO prdctEvltnFigureVO);

    // 평가업적산출기준
    void insPrdtEvluStdd(PrdctEvltnStdVO prdctEvltnStdVO);
    void delPrdctEvltnStd(PrdctEvltnStdVO prdctEvltnStdVO);

    // 지급율 조정계수
    void insPrdctPymntFigure(PrdctPymntFigureVO prdctPymntFigureVO);
    void delPrdctPymntFigure(PrdctPymntFigureVO prdctPymntFigureVO);

    // 수수료유형별조정율
    void insPrdctExtpyAdjst(PrdctExtpyAdjstVO prdctExtpyAdjstVO);
    void delPrdctExtpyAdjst(PrdctExtpyAdjstVO prdctExtpyAdjstVO);

    // 납기수수료유형별조정율
    void insPrdctPeriodExtpy(PrdctPeriodExtpyVO prdctPeriodExtpyVO);
    void delPrdctPeriodExtpy(PrdctPeriodExtpyVO prdctPeriodExtpyVO);

    // 지급액공시
    void insPrdctPymntAlarm(PrdctPymntAlarmVO prdctPymntAlarmVO);
    void delPrdctPymntAlarm(PrdctPymntAlarmVO prdctPymntAlarmVO);

    // 보험사환산
    void insPrdctIrch(PrdctIrchVO prdctIrchVO);
    void delPrdctIrch(PrdctIrchVO prdctIrchVO);

    // 보험사조정율
    void insPrdctInsrrAdjst(PrdctInsrrAdjstVO prdctInsrrAdjstVO);
    void updPrdctInsrrAdjst(PrdctInsrrAdjstVO prdctInsrrAdjstVO);
    void delPrdctInsrrAdjst(PrdctInsrrAdjstVO prdctInsrrAdjstVO);

}
