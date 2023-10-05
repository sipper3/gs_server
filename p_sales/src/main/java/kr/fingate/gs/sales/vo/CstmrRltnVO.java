package kr.fingate.gs.sales.vo;

import org.apache.ibatis.type.Alias;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Alias("CstmrRltnVO")
public class CstmrRltnVO extends BaseVO {

    @Info(value="관계자순번")
    private long rltnUserSeq;

    @Info(value="고객번호")
    private long cstmrNo;

    @Info(value="관계자코드")
    private String rltnUserCode;

    @Info(value="관계자명")
    private String rltnUserName;

    @Info(value="주민(식별)번호")
    private String idntnNo;

    @Info(value="생년월일")
    private String brthd;

    @Info(value="성별")
    private String gender;

    @Info(value="휴대전화번호")
    private String clphnNo;

    @Info(value="자택전화번호")
    private String tlphnNo;

    @Info(value="이메일")
    private String email;

    @Info(value="자택우편번호")
    private String homeZpcd;

    @Info(value="자택주소")
    private String homeAdres;

    @Info(value="자택주소상세")
    private String homeAdresDtl;

    @Info(value="운전여부")
    private String driveYn;

    @Info(value="결혼여부")
    private String mrgYn;

    @Info(value="개인정보활용동의여부")
    private String infoUseAgrmnYn;

    @Info(value="관계자메모")
    private String rltnUserMemo;

    @Info(value="데이터상태")
    private String dataState;

}
