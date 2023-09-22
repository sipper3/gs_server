package kr.fingate.gs.auth.role.dto;

import kr.fingate.gs.comon.vo.BaseVO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RoleItemDto extends BaseVO {

	private Long roleNo;
	private Long itemNo;
	private String useYn;
	private String funcId;

}
