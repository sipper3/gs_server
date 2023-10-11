package kr.fingate.gs.prdctm.rprsn.dto;

import kr.fingate.gs.common.vo.PageVO;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Setter
@Getter
@Alias("SearchPrdctDto")
public class SearchRprsnPrdctDto extends PageVO {

}
