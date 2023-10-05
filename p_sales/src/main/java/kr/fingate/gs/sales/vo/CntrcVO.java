package kr.fingate.gs.sales.vo;

import org.apache.ibatis.type.Alias;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Alias("CntrcVO")
public class CntrcVO extends BaseVO {

    @Info(value="계약번호")
    private long cntrcNo;

    @Info(value="상담번호")
    private long cnslNo;

    @Info(value="고객번호")
    private long cstmrNo;

    @Info(value="설계번호")
    private String designNo;

    @Info(value="상품구분")
    private String prdctClass;

    @Info(value="계약일")
    private String cntrcDate;

    @Info(value="계약시간")
    private String cntrcTime;

    @Info(value="처리상태")
    private String prcsnState;

    @Info(value="출력상태")
    private String printState;

    @Info(value="자필회수여부")
    private String hndwrRecallYn;

    @Info(value="자필상태")
    private String hndwrState;

    @Info(value="보험사코드")
    private String insrrCode;

    @Info(value="대표상품코드")
    private int rprsnPrdctCode;

    @Info(value="상품코드")
    private int prdctCode;

    @Info(value="판매방법")
    private String saleMethod;

    @Info(value="보험사설계번호")
    private String insrrDesignNo;

    @Info(value="증권번호")
    private String policyNo;

    @Info(value="우편물수령지우편번호")
    private String dmZpcd;

    @Info(value="우편물수령지주소")
    private String dmAdres;

    @Info(value="우편물수령지상세")
    private String dmAdresDtl;

    @Info(value="우편물수령연락처")
    private String dmTlphnNo;

    @Info(value="자필서명여부")
    private String hndwrSgntrYn;

    @Info(value="가입설계동의여부")
    private String designAgrmnYn;

    @Info(value="계약체결동의여부")
    private String cntrcAgrmnYn;

    @Info(value="상품소개동의여부")
    private String prdctAgentAgrmnYn;

    @Info(value="LMS동의여부")
    private String lmsAgrmnYn;

    @Info(value="유선해지여부")
    private String tlphnCancelYn;

    @Info(value="과세여부")
    private String taxYn;

    @Info(value="마감여부")
    private String closeYn;

    @Info(value="마감확정자")
    private long closeDfntnUserNo;

    @Info(value="마감확정일시")
    private String closeDfntnDt;

    @Info(value="사용인번호")
    private long userNo;

    @Info(value="담당자")
    private long rspnsUserNo;

    @Info(value="타사계약여부(이관)")
    private String otherCmpnyCntrcYn;

    @Info(value="타인계약여부(이관)")
    private String otherPersonCntrcYn;

    @Info(value="법인계약여부")
    private String corpCntrcYn;

    @Info(value="세부유형")
    private String dtlType;

    @Info(value="최종환수계약상태")
    private String finalRdmptCntrcState;

    @Info(value="매체코드")
    private String mediaCode;

    @Info(value="계약승인(승락)일")
    private String cntrcAcptnDate;

    @Info(value="모집자")
    private long orgUserNo;

    @Info(value="법인DB계약여부")
    private String corpDbCntrcYn;

    @Info(value="이전계약번호")
    private long prevCntrcNo;

    @Info(value="원수사앱전용계약여부")
    private String insrrAppCntrcYn;

    @Info(value="협업번호")
    private long cprtnNo;

    @Info(value="할증승인여부")
    private String extcgAcptnYn;

    @Info(value="서류첨부여부")
    private String docAttachYn;

    @Info(value="법인DB계약유형")
    private String corpDbCntrcType;

    @Info(value="법인DB번호")
    private long corpDbNo;

    @Info(value="신원증명발급기관")
    private String idntfCrtfcIssueInstt;

    @Info(value="신원증명발급일")
    private String idntfCrtfcIssueDate;

    @Info(value="운전면허발급기관")
    private String driveLcnsIssueInstt;

    @Info(value="운전면허발급")
    private String driveLcnsIssue;

    @Info(value="운전면허일련번호")
    private String driveLcnsSerialNo;

    @Info(value="운전면허번호")
    private String driveLcnsNo;

    @Info(value="타사이관유형")
    private String otherCmpnyTrnsfType;

    @Info(value="태아등재일")
    private String unbornModDate;

    @Info(value="출산후정산여부")
    private String prcrtAfterCalcYn;

    @Info(value="마감기준일")
    private String closeStdDate;

    @Info(value="태아등재차감회차")
    private int unbornModDdctnEpsd;

    @Info(value="프로모션DB여부")
    private String prmtnDbYn;

    @Info(value="배서상태")
    private String endrsState;

    @Info(value="데이터상태")
    private String dataStatus;

    @Info(value="최종요청일")
    private String finalRqstDate;

    @Info(value="최종요청처리일")
    private String finalPrcsnDate;

    @Info(value="최종처리일시")
    private String finalPrcsnDt;

    @Info(value="최종처리자")
    private long finalPrcsnUserNo;

    @Info(value="최종납입년월")
    private String finalPymntYymm;

    @Info(value="최종납입회차")
    private int finalPymntEpsd;

    @Info(value="최종정산회차")
    private int finalCmsnEpsd;

    @Info(value="메모")
    private String memo;

}
