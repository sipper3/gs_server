package kr.fingate.gs.sales.vo;

import org.apache.ibatis.type.Alias;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Alias("MdchtTakovCtgryVO")
public class MdchtTakovCtgryVO extends BaseVO {

    @Info(value="분류번호")
    private long ctgryNo;

    @Info(value="분류코드")
    private String ctgryCode;

    @Info(value="분류명")
    private String ctgryName;

    @Info(value="특약코드")
    private String riderCode;

    @Info(value="특약명")
    private String riderName;

}
