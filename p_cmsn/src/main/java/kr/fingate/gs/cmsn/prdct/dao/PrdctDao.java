package kr.fingate.gs.cmsn.prdct.dao;

import com.github.pagehelper.Page;
import kr.fingate.gs.cmsn.prdct.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PrdctDao {

    Page<RprsnPrdctDto> getRprsnPrdctList(SearchRprsnPrdctDto params);

    List<PrdctRateMngmnDto> getPrdctRateMngmnList(SearchPrdctRateMngmnDto params);

    List<PrdctEvltnFigureDto> getPrdctEvltnFigureList(SearchPrdctRateSeqDto params)  throws Exception;

    List<PrdctEvltnStdDto> getPrdctEvltnStdList(SearchPrdctRateSeqDto params)  throws Exception;

    List<PrdctExtpyAdjstDto> getPrdctExtpyAdjstList(SearchPrdctRateSeqDto params)  throws Exception;

    List<PrdctIrchDto> getPrdctIrchList(SearchPrdctRateSeqDto params)  throws Exception;

    List<PrdctPeriodExtpyDto> getPrdctPeriodExtpyList(SearchPrdctRateSeqDto params)  throws Exception;

    List<PrdctPymntAlarmDto> getPrdctPymntAlarmList(SearchPrdctRateSeqDto params)  throws Exception;

    List<PrdctPymntFigureDto> getPrdctPymntFigureList(SearchPrdctRateSeqDto params)  throws Exception;


}
