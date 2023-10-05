package kr.fingate.gs.sales.vo;

import org.apache.ibatis.type.Alias;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Alias("PolicyReisuPrcsnVO")
public class PolicyReisuPrcsnVO extends BaseVO {

    @Info(value="재발행순번")
    private long reisuSeq;

    @Info(value="처리순번")
    private long prcsnSeq;

    @Info(value="처리상태")
    private String prcsnState;

    @Info(value="처리내용")
    private String prcsnCntnt;

}
