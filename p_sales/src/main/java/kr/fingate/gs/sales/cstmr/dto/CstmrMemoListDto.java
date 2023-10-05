package kr.fingate.gs.sales.cstmr.dto;

import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.sales.vo.CstmrMemoVO;
import lombok.*;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Builder
@Alias("CstmrMemoListDto")
public class CstmrMemoListDto extends CstmrMemoVO {

    @Info("등록자조직번호")
    private long regOrgnzNo;

    @Info("등록자조직명")
    private String regOrgnzName;

}
