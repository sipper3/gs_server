package kr.fingate.gs.cmsn.prdct.dto;

import kr.fingate.gs.common.annotation.Info;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Setter
@Getter
@Alias("LastDfntnAplctEndDateDto")
public class LastDfntnAplctEndDateDto {

    @Info("최종적용종료일")
    private String maxAplctEndDate;

    @Info("이전적용종료일")
    private String minAplctEndDate;

    @Info("확정여부")
    private String dfntnYn;

    @Info("타순번기간포함여부")
    private String inclsYn;
}
