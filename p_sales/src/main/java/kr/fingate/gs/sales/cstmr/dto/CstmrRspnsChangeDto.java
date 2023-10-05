package kr.fingate.gs.sales.cstmr.dto;

import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.sales.vo.CstmrRspnsChangeVO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Builder
@Alias("CstmrRspnsChangeDto")
public class CstmrRspnsChangeDto extends CstmrRspnsChangeVO {

    @Info("기존담당자명")
    private String exstnRspnsUserName;

    @Info("기존담당자 조직번호")
    private long exstnRspnsOrgnzNo;

    @Info("기존담당자 조직명")
    private String exstnRspnsOrgnzName;

    @Info("현재담당자명")
    private String crntRspnsUserName;

    @Info("현재담당자 조직번호")
    private long crntRspnsOrgnzNo;

    @Info("현재담당자 조직명")
    private String crntRspnsOrgnzName;

    @Info("등록자 조직번호")
    private long regOrgnzNo;

    @Info("등록자 조직명")
    private String regOrgnzName;
}
