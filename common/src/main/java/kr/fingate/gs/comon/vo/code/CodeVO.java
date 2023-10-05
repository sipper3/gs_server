package kr.fingate.gs.comon.vo.code;

import kr.fingate.gs.comon.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@Alias("CodeVO")
public class CodeVO extends BaseVO {

	// 코드이니셜
	private  String codeInit;

	// 코드ID
	private  String codeId;

	// 코드명
	private  String codeName;

	// 코드이명
	private  String codeNcknm;

	// 출력순서
	private  int printOrder;

	// 사용여부
	private  String useYn;

	// 노출여부
	private  String dispYn;

	// 코드설명
	private  String codeDscrp;

}
