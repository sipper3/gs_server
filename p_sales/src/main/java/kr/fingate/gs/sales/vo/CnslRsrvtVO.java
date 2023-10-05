package kr.fingate.gs.sales.vo;

import org.apache.ibatis.type.Alias;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Alias("CnslRsrvtVO")
public class CnslRsrvtVO extends BaseVO {

    @Info(value="예약순번")
    private long rsrvtSeq;

    @Info(value="상담번호")
    private long cnslNo;

    @Info(value="예약구분")
    private String rsrvtClass;

    @Info(value="예약자구분")
    private String rsrvtUserClass;

    @Info(value="예약일")
    private String rsrvtDate;

    @Info(value="예약시간")
    private String rsrvtTime;

    @Info(value="예약자명")
    private String rsrvtUserName;

    @Info(value="예약전화번호")
    private String rsrvtTlphnNo;

    @Info(value="관계자코드")
    private String rltnUserCode;

    @Info(value="예약메모")
    private String rsrvtMemo;

    @Info(value="데이터상태")
    private String dataState;

}
