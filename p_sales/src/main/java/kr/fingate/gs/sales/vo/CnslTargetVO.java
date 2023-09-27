package kr.fingate.gs.sales.vo;

import kr.fingate.gs.comon.annotation.Info;
import kr.fingate.gs.comon.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CnslTargetVO extends BaseVO {

    @Info(value="상담번호")
    private long cnslNo;

    @Info(value="보험대상자코드")
    private String insprsCode;

    @Info(value="성별")
    private String gender;

    @Info(value="생년월일")
    private String brthd;

    @Info(value="보험대상자명")
    private String insprsName;

    @Info(value="직업코드")
    private String jobCode;

    @Info(value="운전코드")
    private String driveCode;

    @Info(value="장애여부")
    private String obstcYn;

    @Info(value="병력여부")
    private String mdchtYn;

    @Info(value="태아여부")
    private String unbornYn;

    @Info(value="출산예정일")
    private String duedt;

}
