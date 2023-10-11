package kr.fingate.gs.hr.vo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Alias("EmpQlfctVO")
public class EmpQlfctVO extends BaseVO {

    @Info(value="피고용인번호")
    private long empNo;

    @Info(value="면허순차")
    private long qlfctSeq;

    @Info(value="자격코드")
    private String qlfctCode;

    @Info(value="자격/면허명")
    private String qlfctName;

    @Info(value="등록(자격)번호")
    private String qlfctNo;

    @Info(value="발급기관")
    private String issueInstt;

    @Info(value="자격등급")
    private String grad;

    @Info(value="자격만료일")
    private String exprtDate;

    @Info(value="합격일")
    private String passDate;

    @Info(value="발행일")
    private String pblctDate;

    @Info(value="데이터상태")
    private String dataState;

    @Info(value="가상ID")
    private java.sql.Timestamp vrtlId;

}
