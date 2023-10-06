package kr.fingate.gs.dballot.db.dto;

import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.PageVO;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.List;
@Getter
@Setter
@Alias("DbSearchDto")
public class DbSearchDto extends PageVO {

    @Info(value="DB번호")
    private long dbNo;

    @Info(value="상담번호")
    private long cnslNo;

    @Info(value="이름")
    private String name;

    @Info(value="휴대전화")
    private String clphnNo;

    @Info(value="DB분배상태")
    private String dbDstrbState;

    @Info("등록 시작일")
    private String regStDate;

    @Info("등록 종료일")
    private String regEdDate;

    @Info(value="유입 시작일시")
    private String inflowStDt;

    @Info(value="유입 시작일시")
    private String inflowEdDt;

    @Info(value="DB유형")
    private String dbType;

    @Info(value="조직")
    private List<Long> orgnzList;

    @Info(value="배정자")
    private long altmnUserNo;

    @Info(value="(미)배정사유")
    private String altmnReason;

    @Info(value="상품종류")
    private String prdctKind;

    @Info(value="매체코드 리스트")
    private List<String> mediaCodeList;

    @Info(value="상담신청유형")
    private String cnslAplctType;

    @Info(value="성별")
    private String gender;

    @Info(value="시도코드")
    private String ctprvCode;
}
