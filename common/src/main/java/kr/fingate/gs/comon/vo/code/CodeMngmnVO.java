package kr.fingate.gs.comon.vo.code;

import kr.fingate.gs.comon.vo.BaseVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CodeMngmnVO extends BaseVO {

	// 코드이니셜
	private  String codeInit;

	// 코드명
	private  String codeInitName;

	// 사용여부
	private  String useYn;

}