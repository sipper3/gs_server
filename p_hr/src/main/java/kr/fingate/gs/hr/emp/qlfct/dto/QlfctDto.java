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
    @Info(value="자격등록여부")
    private String regCheck;

    @Info(value="자격번호수정여부")
    private String regUpdateCheck;

    @Info(value="자격유효여부")
    private String dateCheck;
}
