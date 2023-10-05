package kr.fingate.gs.sales.cstmr.dto;

import kr.fingate.gs.common.annotation.Info;
import lombok.*;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Builder
@Alias("CstmrListDto")
public class CstmrListDto {

    @Info("고객번호")
    private long cstmrNo;

    @Info("고객명")
    private String cstmrName;

    @Info("휴대전화번호")
    private String clphnNo;

    @Info("주민등록번호")
    private String idntnNo;

    @Info("성별")
    private String gender;

    @Info("생년월일")
    private String brthd;

    @Info("고객유형")
    private String cstmrType;

    @Info("정보활용동의여부")
    private String prvcyUseAgrmnYn;

    @Info("마케팅수신동의여부")
    private String mrktnRcvAgrmnYn;

    @Info("담당자")
    private long rspnsUserNo;

    @Info("담당자명")
    private String rspnsUserName;

    @Info("담당자 조직번호")
    private long rspnsOrgnzNo;

    @Info("담당자 조직명")
    private String rspnsOrgnzName;

}
