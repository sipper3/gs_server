package kr.fingate.gs.sales.cstmr.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import kr.fingate.gs.common.annotation.Encrypt;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.sales.cstmr.dto.validate.CstmrVG;
import kr.fingate.gs.sales.vo.CstmrVO;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("CstmrInsDto")
public class CstmrModDto extends CstmrVO {

    @Min(value = 0, groups = CstmrVG.Ins.class)
    @Info("고객명")
    private long cstmrNo;

    @NotNull
    @Info("고객명")
    private String cstmrName;

    @Encrypt
    @Info(value="주민(식별)번호")
    private String idntnNo;

    @Info(value="생년월일")
    private String brthd;

    @Info(value="성별")
    private String gender;

    @NotEmpty
    @Encrypt
    @Info(value="휴대전화번호")
    private String clphnNo;

    @Info(value="팩스번호")
    private String faxNo;

    @Info(value="직장우편번호")
    private String officeZpcd;

    @Info(value="직장주소")
    private String officeAdres;

    @Info(value="직장주소상세")
    private String officeAdresDtl;

    @Info(value="부서")
    private String dprtm;

    @Info(value="직위")
    private String pstn;

    @Info(value="결혼여부")
    private String mrgYn;

    @Info(value="결혼기념일")
    private String mrgAnvrs;

    @Info(value="운전코드")
    private String driveCode;

    @Info(value="세대수")
    private int noh;
}
