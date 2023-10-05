package kr.fingate.gs.sales.vo;

import org.apache.ibatis.type.Alias;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Alias("CntrcMemoVO")
public class CntrcMemoVO extends BaseVO {

    @Info(value="계약번호")
    private long cntrcNo;

    @Info(value="메모번호")
    private long memoNo;

    @Info(value="메모")
    private String memo;

    @Info(value="메모구분")
    private String memoClass;

}
