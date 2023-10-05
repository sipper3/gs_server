package kr.fingate.gs.sales.cstmr.dto;

import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.sales.vo.CstmrVO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Builder
@Alias("CstmrDto")
public class CstmrDto extends CstmrVO {

    @Info("담당자")
    private long rspnsUserNo;

    @Info("담당자명")
    private String rspnsUserName;

    @Info("담당자 조직번호")
    private long rspnsOrgnzNo;

    @Info("담당자 조직명")
    private String rspnsOrgnzName;

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
