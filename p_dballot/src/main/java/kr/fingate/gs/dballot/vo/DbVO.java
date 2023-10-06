package kr.fingate.gs.dballot.vo;

import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("DbVO")
public class DbVO extends BaseVO {

    @Info(value="DB번호")
    private long dbNo;

    @Info(value="유입요청구분")
    private String inflowRqstClass;

    @Info(value="상담번호")
    private long cnslNo;

    @Info(value="DB유형")
    private String dbType;

    @Info(value="DB대상구분")
    private String dbTargetClass;

    @Info(value="제휴사고객키")
    private String prtnrCstmrKey;

    @Info(value="DB분배상태")
    private String dbDstrbState;

    @Info(value="최종배정일")
    private String finalAltmnDate;

    @Info(value="배정자")
    private long altmnUserNo;

    @Info(value="최종회수일")
    private String finalRecallDate;

    @Info(value="룰관리번호")
    private long ruleMngmnNo;

    @Info(value="마스터룰관리번호")
    private long materRuleMngmnNo;

    @Info(value="중복여부")
    private String dupYn;

    @Info(value="중복DB번호")
    private long dupDbNo;

    @Info(value="DB확인상태")
    private String dbCnfrmState;

    @Info(value="상담신청유형")
    private String cnslAplctType;

    @Info(value="상담신청유형상세")
    private String cnslAplctTypeDtl;

    @Info(value="매체코드")
    private String mediaCode;

    @Info(value="보험사코드")
    private String insrrCode;

    @Info(value="상품종류")
    private String prdctKind;

    @Info(value="상품명")
    private String prdctName;

    @Info(value="유입일시")
    private String inflowDt;

    @Info(value="이름")
    private String name;

    @Info(value="생년월일")
    private String brthd;

    @Info(value="성별")
    private String gender;

    @Info(value="휴대전화")
    private String clphnNo;

    @Info(value="추가전화")
    private String adtnTlphnNo;

    @Info(value="안심번호")
    private String safeTlphnNo;

    @Info(value="이메일")
    private String email;

    @Info(value="시도코드")
    private String ctprvCode;

    @Info(value="시군구코드")
    private String twnshCode;

    @Info(value="근무지역")
    private String workRegion;

    @Info(value="전환대상번호")
    private long chngvTargetNo;

    @Info(value="이전DB번호")
    private long prevDbNo;

    @Info(value="데이터상태")
    private String dataState;

}
