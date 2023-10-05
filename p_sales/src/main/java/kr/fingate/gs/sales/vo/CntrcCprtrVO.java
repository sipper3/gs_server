package kr.fingate.gs.sales.vo;

import org.apache.ibatis.type.Alias;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.BaseVO;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Alias("CntrcCprtrVO")
public class CntrcCprtrVO extends BaseVO {

    @Info(value="계약번호")
    private long cntrcNo;

    @Info(value="협력자사용인번호")
    private long cprtrUserNo;

    @Info(value="협력구분")
    private String cprtnClass;

    @Info(value="역할유형")
    private String roleType;

    @Info(value="업적비율")
    private BigDecimal resultRate;

    @Info(value="수당비율")
    private BigDecimal extpyRate;

}
