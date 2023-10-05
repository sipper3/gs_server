package kr.fingate.gs.sales.vo;

import org.apache.ibatis.type.Alias;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Alias("CnslPrcsnVO")
public class CnslPrcsnVO extends BaseVO {

    @Info(value="처리순번")
    private long prcsnSeq;

    @Info(value="상담번호")
    private long cnslNo;

    @Info(value="상담방법")
    private String cnslMethod;

    @Info(value="상담상태")
    private String cnslState;

    @Info(value="상담상태상세")
    private String cnslStateDtl;

    @Info(value="처리구분")
    private String prcsnClass;

    @Info(value="상담내용")
    private String cnslCntnt;

    @Info(value="상담일")
    private String cnslDate;

    @Info(value="상담시간")
    private String cnslTime;

    @Info(value="등록키")
    private int regKey;

}
