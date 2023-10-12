package kr.fingate.gs.cmsn.prdct.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PrdtRateDtalDto {
    
    List<PrdctEvltnFigureDto> prdctEvltnFigureList;
    List<PrdctEvltnStdDto> prdctEvltnStdList;
    List<PrdctExtpyAdjstDto> prdctExtpyAdjstList;
    List<PrdctIrchDto> prdctIrchList;
    List<PrdctPeriodExtpyDto> prdctPeriodExtpyList;
    List<PrdctPymntAlarmDto> prdctPymntAlarmList;
    List<PrdctPymntFigureDto> prdctPymntFigureList;

}
