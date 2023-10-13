package kr.fingate.gs.cmsn.prdct.dao;

import com.github.pagehelper.Page;
import kr.fingate.gs.cmsn.prdct.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PrdctDao {

    Page<RprsnPrdctDto> getRprsnPrdctList(SearchRprsnPrdctDto params);

    List<PrdctRateMngmnDto> getPrdctRateMngmnList(SearchPrdctRateMngmnDto params);

    List<PrdctEvltnFigureDto> getPrdctEvltnFigureList(SearchPrdctRateSeqDto params);

    List<PrdctEvltnStdDto> getPrdctEvltnStdList(SearchPrdctRateSeqDto params);

    List<PrdctExtpyAdjstDto> getPrdctExtpyAdjstList(SearchPrdctRateSeqDto params);

    List<PrdctIrchDto> getPrdctIrchList(SearchPrdctRateSeqDto params);

    List<PrdctPeriodExtpyDto> getPrdctPeriodExtpyList(SearchPrdctRateSeqDto params);

    List<PrdctPymntAlarmDto> getPrdctPymntAlarmList(SearchPrdctRateSeqDto params);

    List<PrdctPymntFigureDto> getPrdctPymntFigureList(SearchPrdctRateSeqDto params);

    int getMaxPrdctRateSeq(SearchPrdctRateSeqDto params);

    LastDfntnAplctEndDateDto getLastDfntnAplctEndDate(PrdctRateMngmnDto params);
}
