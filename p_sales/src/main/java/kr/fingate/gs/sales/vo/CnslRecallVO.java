package kr.fingate.gs.sales.vo;

import org.apache.ibatis.type.Alias;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Alias("CnslRecallVO")
public class CnslRecallVO extends BaseVO {

    @Info(value="회수순번")
    private long recallSeq;

    @Info(value="상담번호")
    private long cnslNo;

    @Info(value="회수상태")
    private String recallState;

    @Info(value="회수사유코드")
    private String reasonCode;

    @Info(value="회수태그")
    private String recallTag;

}
