package kr.fingate.gs.hr.emp.dto;

import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.PageVO;
import lombok.*;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Alias("EmpSearchDto")
public class EmpSearchDto extends PageVO {
    @Info(value="피고용인번호")
    private long empNo;
}




