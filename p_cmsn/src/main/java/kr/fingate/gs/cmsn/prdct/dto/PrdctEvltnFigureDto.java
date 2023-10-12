package kr.fingate.gs.cmsn.prdct.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import kr.fingate.gs.cmsn.vo.prdct.PrdctEvltnFigureVO;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Alias("PrdctEvltnFigureDto")
public class PrdctEvltnFigureDto extends PrdctEvltnFigureVO {
}
