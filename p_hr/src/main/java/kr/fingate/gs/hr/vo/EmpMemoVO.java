package kr.fingate.gs.hr.vo;

import org.apache.ibatis.type.Alias;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Alias("EmpMemoVO")
public class EmpMemoVO extends BaseVO {

    @Info(value="피고용인번호")
    private long empNo;

    @Info(value="메모순차")
    private long memoSeq;

    @Info(value="메모구분")
    private String memoClass;

    @Info(value="메모내용")
    private String memo;

    @Info(value="데이터상태")
    private String dataState;

}
