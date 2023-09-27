package kr.fingate.gs.sales.vo;

import kr.fingate.gs.comon.annotation.Info;
import kr.fingate.gs.comon.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CnslGiftRqstVO extends BaseVO {

    @Info(value="상담번호")
    private long cnslNo;

    @Info(value="DB번호")
    private long dbNo;

    @Info(value="배송우편번호")
    private String dlvryZpcd;

    @Info(value="배송주소")
    private String dlvryAdres;

    @Info(value="배송주소상세")
    private String dlvryAdresDtl;

    @Info(value="수령자명")
    private String rcvUserName;

    @Info(value="수령자연락처")
    private String rcvUserTlphnNo;

    @Info(value="불일치사유")
    private String dscrdReason;

    @Info(value="사은품처리상태")
    private String giftPrcsnState;

    @Info(value="배송일자")
    private String dlvryDate;

    @Info(value="택배사")
    private String dlivCmpny;

    @Info(value="운송장번호")
    private String trkNo;

    @Info(value="메모")
    private String memo;

    @Info(value="데이터상태")
    private String dataState;

}
