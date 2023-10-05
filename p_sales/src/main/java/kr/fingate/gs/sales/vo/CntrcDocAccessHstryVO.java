package kr.fingate.gs.sales.vo;

import org.apache.ibatis.type.Alias;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.BaseVO;
import java.math.BigInteger;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Alias("CntrcDocAccessHstryVO")
public class CntrcDocAccessHstryVO extends BaseVO {

    @Info(value="이력순번")
    private long hstrySeq;

    @Info(value="고객인증키")
    private BigInteger cstmrAuthKey;

    @Info(value="생년월일/사업자번호")
    private String brthd;

    @Info(value="본인인증성공여부")
    private String authSuccYn;

    @Info(value="접속IP")
    private String accessIp;

    @Info(value="접속기기")
    private String accessAgent;

}
