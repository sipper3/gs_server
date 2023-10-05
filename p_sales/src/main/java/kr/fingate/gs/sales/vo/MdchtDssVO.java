package kr.fingate.gs.sales.vo;

import org.apache.ibatis.type.Alias;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Alias("MdchtDssVO")
public class MdchtDssVO extends BaseVO {

    @Info(value="특정질병번호")
    private long mdchtDssNo;

    @Info(value="보험사코드")
    private String insrrCode;

    @Info(value="특정질병대수")
    private int mdchtDssCount;

    @Info(value="데이터상태")
    private String dataState;

}
