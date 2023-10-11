package kr.fingate.gs.hr.emp.edu.dto;

import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.hr.vo.EmpEduVO;
import lombok.*;
import org.apache.ibatis.type.Alias;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Alias("EduDto")
public class EduDto extends EmpEduVO {
    @Info(value="교육구분명")
    private String eduClassNm;
}
