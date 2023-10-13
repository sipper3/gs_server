package kr.fingate.gs.common.external.excel.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 워크 북의 정보를 얻을 핸들러
 */
public class WorkbookHandler extends DefaultHandler {

	protected static final Logger logger = LoggerFactory.getLogger(WorkbookHandler.class);

	// 행 태그
	private static final String TAG_SHT = "sheet";
	// 시트 이름 속성 이름
	private static final String NM_SNM = "name";
	// r : id 속성 이름
	private static final String NM_RID = "r:id";
	// 대상 시트
	private String sheetName = null;
	// 대상 시트 r:id
	private String rId = null;

	public String getrId() {
		return rId;
	}

	public WorkbookHandler(String sheetName) {
		this.sheetName = sheetName;
	}

	// 시작 태그를 취득
	public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
		// 시트 시작 태그의 경우
		if (name.equals(TAG_SHT)) {
			String sheetNameTemp = attributes.getValue(NM_SNM);
			String rIdTemp = attributes.getValue(NM_RID);
			if (sheetName.equals(sheetNameTemp)) {
				rId = rIdTemp;
			}
		}
	}
}
