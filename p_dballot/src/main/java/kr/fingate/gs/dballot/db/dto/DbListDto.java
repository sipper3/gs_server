package kr.fingate.gs.dballot.db.dto;

import kr.fingate.gs.common.annotation.Info;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("DbListDto")
public class DbListDto {

    @Info(value="DB번호")
    private long dbNo;

    @Info(value="DB유형")
    private String dbType;

    @Info(value="중복여부")
    private String dupYn;

    @Info(value="DB분배상태")
    private String dbDstrbState;

    @Info(value="(미)배정사유")
    private String altmnReason;

    @Info(value="배정자")
    private long altmnUserNo;

    @Info(value="유입일시")
    private String inflowDt;

    @Info(value="최종배정일")
    private String finalAltmnDate;

    @Info(value="상담번호")
    private long cnslNo;

    @Info(value="상품종류")
    private String prdctKind;

    @Info(value="매체코드")
    private String mediaCode;

    @Info(value="상담신청유형")
    private String cnslAplctType;

    @Info(value="이름")
    private String name;

    @Info(value="휴대전화")
    private String clphnNo;

    @Info(value="생년월일")
    private String brthd;

    @Info(value="성별")
    private String gender;

    @Info("등록일")
    private String regDate;

}
