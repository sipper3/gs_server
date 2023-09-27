package kr.fingate.gs.sales.vo;

import kr.fingate.gs.comon.annotation.Info;
import kr.fingate.gs.comon.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CntrcClctnUploadVO extends BaseVO {

    @Info(value="등록순번")
    private long regSeq;

    @Info(value="번호")
    private int no;

    @Info(value="계약번호")
    private long cntrcNo;

    @Info(value="수금이관사용인번호")
    private long clctnTrnsfUserNo;

    @Info(value="성공여부")
    private String succYn;

    @Info(value="실패사유")
    private String failReason;

}
