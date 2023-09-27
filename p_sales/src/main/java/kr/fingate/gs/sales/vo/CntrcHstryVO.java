package kr.fingate.gs.sales.vo;

import kr.fingate.gs.comon.annotation.Info;
import kr.fingate.gs.comon.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CntrcHstryVO extends BaseVO {

    @Info(value="이력순번")
    private long hstrySeq;

    @Info(value="실체키")
    private int enttyKey;

    @Info(value="실체명")
    private String enttyName;

    @Info(value="속성명")
    private String atrbtName;

    @Info(value="본래값")
    private String orgnlValue;

    @Info(value="변경값")
    private String changeValue;

    @Info(value="작업유형")
    private String oprtnType;

}
