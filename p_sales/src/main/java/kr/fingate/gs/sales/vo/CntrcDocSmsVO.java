package kr.fingate.gs.sales.vo;

import org.apache.ibatis.type.Alias;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Alias("CntrcDocSmsVO")
public class CntrcDocSmsVO extends BaseVO {

    @Info(value="SMS발송순번")
    private long smsSendSeq;

    @Info(value="고객명")
    private String cstmrName;

    @Info(value="생년월일/사업자번호")
    private String brthd;

    @Info(value="휴대전화")
    private String clphnNo;

    @Info(value="접속실패횟수")
    private int accessFailTimes;

}
