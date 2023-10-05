package kr.fingate.gs.sales.vo;

import org.apache.ibatis.type.Alias;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Alias("CntrcEndrsDtlVO")
public class CntrcEndrsDtlVO extends BaseVO {

    @Info(value="계약번호")
    private long cntrcNo;

    @Info(value="배서번호")
    private long endrsNo;

    @Info(value="등록순번")
    private long regSeq;

    @Info(value="실체명")
    private String enttyName;

    @Info(value="속성명")
    private String atrbtName;

    @Info(value="본래값")
    private String orgnlValue;

    @Info(value="변경값")
    private String changeValue;

    @Info(value="데이터상태")
    private String dataStatus;

}
