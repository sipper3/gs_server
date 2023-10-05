package kr.fingate.gs.sales.vo;

import org.apache.ibatis.type.Alias;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Alias("CstmrVO")
public class CstmrVO extends BaseVO {

    @Info(value="고객번호")
    private long cstmrNo;

    @Info(value="고객명")
    private String cstmrName;

    @Info(value="주민(식별)번호")
    private String idntnNo;

    @Info(value="생년월일")
    private String brthd;

    @Info(value="생년월일음력여부")
    private String brthdLunarYn;

    @Info(value="성별")
    private String gender;

    @Info(value="휴대전화번호")
    private String clphnNo;

    @Info(value="휴대전화번호4자리")
    private String clphnNo4Dgt;

    @Info(value="휴대전화인증여부")
    private String clphnAuthYn;

    @Info(value="자택전화번호")
    private String tlphnNo;

    @Info(value="자택전화번호4자리")
    private String tlphnNo4Dgt;

    @Info(value="추가전화번호")
    private String adtnTlphnNo;

    @Info(value="추가전화번호4자리")
    private String adtnTlphnNo4Dgt;

    @Info(value="이메일")
    private String email;

    @Info(value="시도코드")
    private String ctprvCode;

    @Info(value="시군구코드")
    private String twnshCode;

    @Info(value="자택우편번호")
    private String homeZpcd;

    @Info(value="자택주소")
    private String homeAdres;

    @Info(value="자택주소상세")
    private String homeAdresDtl;

    @Info(value="직장명")
    private String officeName;

    @Info(value="직장전화번호")
    private String officeTlphnNo;

    @Info(value="고객구분")
    private String cstmrClass;

    @Info(value="고객유형")
    private String cstmrType;

    @Info(value="고객등록유형")
    private String cstmrRegType;

    @Info(value="등록매체")
    private String regMedia;

    @Info(value="등록키")
    private int regKey;

    @Info(value="담당자")
    private long rspnsUserNo;

    @Info(value="수집경로")
    private String clctnPath;

    @Info(value="이전고객번호")
    private long prevCstmrNo;

    @Info(value="개인정보활용동의여부")
    private String prvcyUseAgrmnYn;

    @Info(value="개인정보활용동의일")
    private String prvcyUseAgrmnDate;

    @Info(value="개인정보활용기간")
    private int prvcyUsePeriod;

    @Info(value="개인정보활용만료일")
    private String prvcyUseExprtDate;

    @Info(value="개인정보활용동의유형")
    private String prvcyUseAgrmnType;

    @Info(value="마케팅수신동의여부")
    private String mrktnRcvAgrmnYn;

    @Info(value="마케팅수신동의일")
    private String mrktnRcvAgrmnDate;

    @Info(value="마케팅수신동의유형")
    private String mrktnRcvAgrmnType;

    @Info(value="전화수신동의여부")
    private String tlphnRcvAgrmnYn;

    @Info(value="SMS수신동의여부")
    private String smsRcvAgrmnYn;

    @Info(value="이메일수신동의여부")
    private String emailRcvAgrmnYn;

    @Info(value="데이터상태")
    private String dataState;

}
