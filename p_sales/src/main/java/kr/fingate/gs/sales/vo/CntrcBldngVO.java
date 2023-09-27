package kr.fingate.gs.sales.vo;

import kr.fingate.gs.comon.annotation.Info;
import kr.fingate.gs.comon.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CntrcBldngVO extends BaseVO {

    @Info(value="계약번호")
    private long cntrcNo;

    @Info(value="건물순차")
    private long bldngSeq;

    @Info(value="가입소재지우편번호")
    private String bldngZpcd;

    @Info(value="가입소재지기본주소")
    private String bldngAdres;

    @Info(value="가입소재지상세주소")
    private String bldngAdresDtl;

    @Info(value="건물유형")
    private String bldngType;

    @Info(value="건물유형기타")
    private String bldngTypeEtc;

    @Info(value="기둥구조")
    private String bldngPl;

    @Info(value="기둥구조기타")
    private String bldngPlEtc;

    @Info(value="지붕구조")
    private String bldngHd;

    @Info(value="지붕구조기타")
    private String bldngHdEtc;

    @Info(value="외벽구조")
    private String bldngOw;

    @Info(value="외벽구조기타")
    private String bldngOwEtc;

    @Info(value="건물지하층수")
    private String bldngUnoudrFloor;

    @Info(value="건물지상층수")
    private String bldngGroundFloor;

    @Info(value="총가입층수")
    private String bldngFloor;

    @Info(value="총가입평수")
    private String bldngTotalPyeong;

    @Info(value="총가입면적")
    private String bldngTotalArea;

    @Info(value="건물소유주")
    private String bldngOwner;

    @Info(value="건물소유주연락처")
    private String bldngOwnerTlphnNo;

    @Info(value="건물소유주주민번호")
    private String bldngOwnerIdntnNo;

    @Info(value="계약자관계코드")
    private String cntrcRltnCode;

    @Info(value="소유주업무")
    private String ownerBsns;

}
