package kr.fingate.gs.hr.emp.qlfct.dto;

import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.hr.vo.EmpQlfctVO;
import lombok.*;
import org.apache.ibatis.type.Alias;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Alias("QlfctDto")
public class QlfctDto extends EmpQlfctVO {
    @Info(value="자격코드명")
    private String qlfctCodeName;

    @Info(value="발급기관명")
    private String issueInsttName;
}
