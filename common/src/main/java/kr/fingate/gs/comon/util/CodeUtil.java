package kr.fingate.gs.comon.util;

import kr.fingate.gs.comon.vo.code.CodeVO;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

public class CodeUtil {

	/**
	 * 공통코드명 조회
	 * @param codeMap
	 * @param codeInit
	 * @param codeId
	 * @return
	 * @author
	 */
	public static String getCodeName(Map<String, List<CodeVO>> codeMap, String codeInit, String codeId) {
		List<CodeVO> codeLst = codeMap.get(codeInit);
		if(CollectionUtils.isEmpty(codeLst)) return null;
		return codeLst.stream()
					.filter(codeItem -> codeItem.getCodeId().equals(codeId))
					.findAny()
					.map(CodeVO::getCodeName)
					.orElse(null);
	}

}
