package kr.fingate.gs.common.external.excel.event;


import kr.fingate.gs.common.external.excel.event.mapping.FieldValueArranger;
import kr.fingate.gs.common.external.excel.event.util.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 시트를 처리 할 handler
 * 여기에 시트의 값을 취득하고 FieldValueArranger에 전달합니다.
 */
@Slf4j
public class SheetHandler extends DefaultHandler {

	// 행 태그
	private static final String TAG_ROW = "row";
	// 열 태그
	private static final String TAG_COL = "c";
	// 값의 태그
	private static final String TAG_VAL = "v";
	// 행 번호의 속성 이름
	private static final String NM_RNO  = "r";
	// 레인지의 속성 이름
	private static final String NM_RNG  = "r";
	// 유형의 속성 이름
	private static final String NM_TYP  = "t";
	// 문자열 유형
	private static final String TYP_STR = "s";
	// 공유하는 문자열 테이블
	private final SharedStringsTable sst;
	// 취득한 값을 처리 클래스
	private FieldValueArranger arranger = null;
	// 직전의 콘텐츠 값
	private String lastContents;
	// 다음 값이 문자열인지 여부
	private boolean nextIsString;
	// 현재 행
	private int currentRow = 0;
	// 현재 열
	private int currentCol = 0;

	public SheetHandler(SharedStringsTable sst, FieldValueArranger arranger) {
		this.sst = sst;
		this.arranger = arranger;
	}

	// 시작 태그 취득
	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {

		// 행 시작 태그의 경우
		if (name.equals(TAG_ROW)) {
			// 행 태그의 경우 줄 번호를 가져 열 번호 지우기
			currentRow = Integer.parseInt(attributes.getValue(NM_RNO));
			currentCol = 0;
		}

		// 열 시작 태그의 경우
		if (name.equals(TAG_COL)) {
			// 범위를 얻음
			String range = attributes.getValue(NM_RNG);
			String rangeCol = ExcelUtils.getFirstColString(range);
			// 현재 열 번호를 가져옴
			currentCol = ExcelUtils.toColIndex(rangeCol);
			// 셀 타입을 취득
			String cellType = attributes.getValue(NM_TYP);
			// 셀 유형이 "s"의 경우 문자열임을 플래그로 설정
			if (cellType != null && cellType.equals(TYP_STR)) {
				nextIsString = true;
			} else {
				nextIsString = false;
			}
		}
		lastContents = "";
	}

	// 종료 태그 취득
	public void endElement(String uri, String localName, String name) throws SAXException {
		// 문자열의 경우 지금 가지고 lastContents는 SharedStringsTable 인덱스이므로 문자열로 대체한다.
		if (nextIsString) {
			// 직전의 값을 숫자(참조 인덱스)로 변환
			int idx = Integer.parseInt(lastContents);
			// 문자열의 경우 SharedStringsTable에 따로 관리되고 있기 때문에, 그쪽에서 검색
			lastContents = new XSSFRichTextString(sst.getEntryAt(idx)).toString();
			nextIsString = false;
		}

		// 값 종료 태그의 경우
		if (name.equals(TAG_VAL)) {
			// lastContents 셀의 값이 들어 있기 때문에 여기에서 bean에 매핑
			try {
				arranger.arrange(currentRow, currentCol, lastContents);
			} catch (Exception e) {
				throw new SAXException("엑셀의 " + currentRow + "행 " + currentCol + "열의 " + lastContents +" 값을 확인해주세요.");
			}
		}
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		lastContents += new String(ch, start, length);
	}
}
