package kr.fingate.gs.sales.vo;

import kr.fingate.gs.comon.annotation.Info;
import kr.fingate.gs.comon.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CstmrAdtnInfoVO extends BaseVO {

    @Info(value="고객번호")
    private long cstmrNo;

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
