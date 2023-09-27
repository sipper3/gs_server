package kr.fingate.gs.sales.vo;

import kr.fingate.gs.comon.annotation.Info;
import kr.fingate.gs.comon.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CntrcInsrrAcntVO extends BaseVO {

    @Info(value="계약번호")
    private long cntrcNo;

    @Info(value="아이디")
    private String id;

    @Info(value="비밀번호")
    private String pwd;

}
