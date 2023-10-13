package kr.fingate.gs.hr.vo;

import org.apache.ibatis.type.Alias;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Alias("EmpEduVO")
public class EmpEduVO extends BaseVO {

    @Info(value="피고용인번호")
    private long empNo;

    @Info(value="교육순차")
    private long eduSeq;

    @Info(value="교육기관")
    private String eduInstt;

    @Info(value="교육과정")
    private String eduPrcss;

    @Info(value="교육시작일")
    private String eduStartDate;

    @Info(value="교육종료일")
    private String eduEndDate;

    @Info(value="수료상태")
    private String cmpltState;

    @Info(value="수료번호")
    private String cmpltNo;

    @Info(value="수료일")
    private String cmpltDate;

    @Info(value="교육구분")
    private String eduClass;

    @Info(value="데이터상태")
    private String dataState;

    @Info(value="가상ID")
    private java.sql.Timestamp vrtlId;

}
