package kr.fingate.gs.sales.vo;

import kr.fingate.gs.comon.annotation.Info;
import kr.fingate.gs.comon.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CntrcInsprsVO extends BaseVO {

    @Info(value="계약번호")
    private long cntrcNo;

    @Info(value="보험대상자코드")
    private String insprsCode;

    @Info(value="성별")
    private String gender;

    @Info(value="생년월일")
    private String brthd;

    @Info(value="고객번호")
    private long cstmrNo;

    @Info(value="소개자명")
    private String agnetName;

    @Info(value="주민번호")
    private String idntnNo;

    @Info(value="주민번호6자리")
    private String idntn6Dgt;

    @Info(value="관계코드")
    private String rltnCode;

    @Info(value="직업코드")
    private String jobCode;

    @Info(value="직업명")
    private String jobName;

    @Info(value="일반위험등급")
    private String gnrlGrade;

    @Info(value="상해위험등급")
    private String acdntDangerGrade;

    @Info(value="운전코드")
    private String driveCode;

    @Info(value="운전코드-기타")
    private String driveCodeEtc;

    @Info(value="부담보여부")
    private String ntgntYn;

    @Info(value="태아여부")
    private String unbornYn;

    @Info(value="할인체코드")
    private String dscntBodyCode;

    @Info(value="직장명")
    private String officeName;

    @Info(value="직위")
    private String pstn;

    @Info(value="부서")
    private String dprtm;

    @Info(value="직무")
    private String duty;

    @Info(value="휴대전화번호")
    private String clphnNo;

    @Info(value="휴대전화4자리")
    private String clphnNo4Dgt;

    @Info(value="자택전화번호")
    private String tlphnNo;

    @Info(value="자택전화4자리")
    private String tlphnNo4Dgt;

    @Info(value="직장전화")
    private String officeTlphnNo;

    @Info(value="이메일")
    private String email;

    @Info(value="자택우편번호")
    private String homeZpcd;

    @Info(value="자택주소")
    private String homeAdres;

    @Info(value="자택주소상세")
    private String homeAdresDtl;

    @Info(value="직장우편번호")
    private String officeZpcd;

    @Info(value="직장주소")
    private String officeAdres;

    @Info(value="직장주소상세")
    private String officeAdresDtl;

    @Info(value="결혼여부")
    private String mrgYn;

}
