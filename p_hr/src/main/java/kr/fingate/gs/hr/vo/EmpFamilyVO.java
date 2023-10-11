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
@Alias("EmpFamilyVO")
public class EmpFamilyVO extends BaseVO {

    @Info(value="피고용인번호")
    private long empNo;

    @Info(value="가족순번")
    private long familySeq;

    @Info(value="관계")
    private String rltn;

    @Info(value="이름")
    private String name;

    @Info(value="주민번호")
    private String idntnNo;

    @Info(value="실제생일")
    private String realBrthd;

    @Info(value="생일음력여부")
    private String brthdLunarYn;

    @Info(value="동거여부")
    private String chbttYn;

    @Info(value="직업")
    private String job;

    @Info(value="근무처")
    private String office;

    @Info(value="직위")
    private String officePstn;

    @Info(value="휴대전화번호")
    private String clphnNo;

    @Info(value="자택전화번호")
    private String homeTlphnNo;

    @Info(value="직장전화번호")
    private String officeTlphnNo;

    @Info(value="데이터상태")
    private String dataState;

    @Info(value="가상ID")
    private java.sql.Timestamp vrtlId;

}
