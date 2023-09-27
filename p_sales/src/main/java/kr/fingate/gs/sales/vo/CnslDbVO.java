package kr.fingate.gs.sales.vo;

import kr.fingate.gs.comon.annotation.Info;
import kr.fingate.gs.comon.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CnslDbVO extends BaseVO {

    @Info(value="상담번호")
    private long cnslNo;

    @Info(value="DB번호")
    private long dbNo;

    @Info(value="배정담당자")
    private long altmnRspnsUserNo;

    @Info(value="메인여부")
    private String mainYn;

    @Info(value="DB유형")
    private String dbType;

    @Info(value="유입일시")
    private String inflowDt;

    @Info(value="배정일시")
    private String altmnDt;

    @Info(value="배정자지정여부")
    private String altmnUserDsgntYn;

    @Info(value="요청구분")
    private String rqstClass;

    @Info(value="요청고유키")
    private String rqstUniqueKey;

    @Info(value="매체코드")
    private String mediaCode;

    @Info(value="파트너ID")
    private String prtnrId;

    @Info(value="키워드")
    private String kywrd;

    @Info(value="개인정보활용동의여부")
    private String prvcyUseAgrmnYn;

    @Info(value="개인정보활용동의일")
    private String prvcyUseAgrmnDate;

    @Info(value="마케팅활용수신동의여부")
    private String mrktnUseAgrmnYn;

    @Info(value="기타1")
    private String etc1;

    @Info(value="기타2")
    private String etc2;

    @Info(value="고객관리동의유형")
    private String cstmrMngmnAgrmnType;

    @Info(value="사은품명")
    private String giftName;

    @Info(value="데이터상태")
    private String dataState;

    @Info(value="휴대전화인증여부")
    private String clphnAuthYn;

    @Info(value="제휴사고객키")
    private String prtnrCstmrKey;

}
