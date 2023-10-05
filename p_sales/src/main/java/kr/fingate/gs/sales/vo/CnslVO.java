package kr.fingate.gs.sales.vo;

import org.apache.ibatis.type.Alias;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Alias("CnslVO")
public class CnslVO extends BaseVO {

    @Info(value="상담번호")
    private long cnslNo;

    @Info(value="고객번호")
    private long cstmrNo;

    @Info(value="상담구분코드")
    private String cnslClassCode;

    @Info(value="상담신청유형")
    private String cnslAplctType;

    @Info(value="상담신청유형상세")
    private String cnslAplctTypeDtl;

    @Info(value="상담상태")
    private String cnslState;

    @Info(value="상담상태상세")
    private String cnslStateDtl;

    @Info(value="DB종류")
    private String dbKind;

    @Info(value="이름")
    private String name;

    @Info(value="생년월일")
    private String brthd;

    @Info(value="생일음력여부")
    private String brthdLunarYn;

    @Info(value="성별")
    private String gender;

    @Info(value="휴대전화번호")
    private String clphnNo;

    @Info(value="기타(추가)전화번호")
    private String etcTlphnNo;

    @Info(value="안심번호")
    private String safeTlphnNo;

    @Info(value="이메일")
    private String email;

    @Info(value="상담요청제목")
    private String cnslRqstTitle;

    @Info(value="상담요청내용")
    private String cnslRqstCntnt;

    @Info(value="거절유형")
    private String rfslType;

    @Info(value="매체코드")
    private String mediaCode;

    @Info(value="보험사코드")
    private String insrrCode;

    @Info(value="상품종류")
    private String prdctKind;

    @Info(value="상품명")
    private String prdctName;

    @Info(value="키워드")
    private String kywrd;

    @Info(value="시도코드")
    private String ctprvCode;

    @Info(value="시군구코드")
    private String twnshCode;

    @Info(value="이전상담번호")
    private long prevCnslNo;

    @Info(value="접속지역")
    private String accessRegion;

    @Info(value="접속도시")
    private String accessCity;

    @Info(value="접속망")
    private String accessNet;

    @Info(value="유입IP")
    private String inflowIp;

    @Info(value="유입일시")
    private String inflowDt;

    @Info(value="데이터상태")
    private String dataState;

    @Info(value="DB유형")
    private String dbType;

}
