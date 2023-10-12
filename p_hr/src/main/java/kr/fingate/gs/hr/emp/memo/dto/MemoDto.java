package kr.fingate.gs.hr.emp.memo.dto;

import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.hr.vo.EmpMemoVO;
import lombok.*;
import org.apache.ibatis.type.Alias;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Alias("MemoDto")
public class MemoDto extends EmpMemoVO {
    @Info(value="교육구분명")
    private String eduClassNm;
}
