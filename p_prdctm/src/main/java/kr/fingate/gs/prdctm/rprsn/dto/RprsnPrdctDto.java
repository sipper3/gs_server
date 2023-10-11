package kr.fingate.gs.prdctm.rprsn.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import kr.fingate.gs.prdctm.vo.RprsnPrdctVO;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Setter
@Getter
@Alias("RprsnPrdctDto")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RprsnPrdctDto extends RprsnPrdctVO {


}
