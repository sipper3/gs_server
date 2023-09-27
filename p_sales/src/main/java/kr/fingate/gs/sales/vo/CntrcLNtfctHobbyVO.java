package kr.fingate.gs.sales.vo;

import kr.fingate.gs.comon.annotation.Info;
import kr.fingate.gs.comon.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CntrcLNtfctHobbyVO extends BaseVO {

    @Info(value="계약번호")
    private long cntrcNo;

    @Info(value="보험대상자코드")
    private String insprsCode;

    @Info(value="취미순번")
    private long hobbySeq;

    @Info(value="취미코드")
    private String hobbyCode;

    @Info(value="기타취미명")
    private String etcHobbyName;

    @Info(value="취미횟수")
    private String hobbyTimes;

    @Info(value="취미자격증명")
    private String hobbyLcnsName;

}
