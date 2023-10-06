package kr.fingate.gs.sales.cstmr.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.PageVO;
import lombok.*;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Getter
@Setter
@Alias("CstmrListSearchDto")
public class CstmrListSearchDto extends PageVO {

    @Info(value = "시스템코드", description = "필수")
    private String systemCode;

    @Info(value="고객번호")
    private long cstmrNo;

    @Info(value="고객명")
    private String cstmrName;

    @Info(value="주민등록번호 및 사업자번호")
    private String idntnNo;

    @Info(value="생년월일")
    private String brthd;

    @Info(value="성별")
    private String gender;

    @Info(value="휴대전화번호")
    private String clphnNo;

    @Info(value="고객유형")
    private String cstmrType;

    @Info(value="개인정보활용동의여부")
    private String prvcyUseAgrmnYn;

    @Info(value="마케팅수신동의여부")
    private String mrktnRcvAgrmnYn;

    @Info(value="담당자")
    private long rspnsUserNo;

    @Info(value="담당자명")
    private String rspnsUserName;

    @Info(value="담당자 조직번호")
    private long rspnsOrgnzNo;

    @JsonIgnore
    @Info(value="담당자 사용자번호(리스트)")
    private List<Long> rspnsUserNoList;

    @JsonIgnore
    @Info(value="담당자 조직번호(리스트)")
    private List<Long> rspnsOrgnzNoList;

    @Info(value="등록일 form")
    private String regStartDate;

    @Info(value="등록일 to")
    private String regEndDate;

    @Info(value="수정일 form")
    private String modStartDate;

    @Info(value="수정일 to")
    private String modEndDate;
}
