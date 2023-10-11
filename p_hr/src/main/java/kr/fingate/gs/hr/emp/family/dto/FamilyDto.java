package kr.fingate.gs.hr.emp.family.dto;

import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.hr.vo.EmpFamilyVO;
import lombok.*;
import org.apache.ibatis.type.Alias;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Alias("FamilyDto")
public class FamilyDto extends EmpFamilyVO {
    @Info(value="관계명")
    private String rltnNm;

    @Info(value="직업명")
    private String jobNm;
}
