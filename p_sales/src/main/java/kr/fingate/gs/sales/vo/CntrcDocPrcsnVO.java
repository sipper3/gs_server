package kr.fingate.gs.sales.vo;

import kr.fingate.gs.comon.annotation.Info;
import kr.fingate.gs.comon.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CntrcDocPrcsnVO extends BaseVO {

    @Info(value="처리번호")
    private long prcsnNo;

    @Info(value="문서번호")
    private long docNo;

    @Info(value="처리상태")
    private String prcsnState;

}
