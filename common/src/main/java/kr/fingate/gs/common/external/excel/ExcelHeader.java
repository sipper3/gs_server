package kr.fingate.gs.common.external.excel;

import kr.fingate.gs.common.external.excel.event.util.ExcelUtils;
import kr.fingate.gs.common.util.ObjectUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections4.map.ListOrderedMap;
import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@NoArgsConstructor
public class ExcelHeader {

	// workbook.getCellStyleAt(2) : center
	// workbook.getCellStyleAt(3) : left
	// workbook.getCellStyleAt(4) : right
	String DEFAULT_ALIGN = "center";


	@Setter
	@Getter
//	@AllArgsConstructor
	public class HeaderCell {
		private String col;
		private String field;
		private String name;
		private int idx;
		private String align;
		private int rowIdx;
		private int rowspan;
		private int colspan;
		private String codeName;
		private String formula;
		private Map codeMap;
		private boolean isRed;
		private boolean isComment;
		private boolean isHeaderCode;

		public HeaderCell(String col
				, String field
				, String name
				, int idx
				, String align
				, int rowIdx
				, int rowspan
				, int colspan
				, String codeName
				, String formula
				, boolean isRed
				, boolean isComment
				, boolean isHeaderCode
				) {
			this.col = col;
			this.field= field;
			this.name=name;
			this.idx=idx;
			this.align=align;
			this.rowIdx=rowIdx;
			this.rowspan=rowspan;
			this.colspan=colspan;
			this.codeName=codeName;
			this.formula=formula;
			this.isRed=isRed;
			this.isComment=isComment;
			this.isHeaderCode=isHeaderCode;
		}
	}

//	private HashMap<String, HeaderCell> headers = new HashMap<>();
	private ListOrderedMap<String, HeaderCell> headers = new ListOrderedMap<>();
	private List<ListOrderedMap<String, HeaderCell>> doubleHeaders = new ArrayList<ListOrderedMap<String, HeaderCell>>();
	private List<String> comments = new ArrayList<String>();
	private int topCommentSize = 0;

	/**
	 * 컬럼 자동 지정(AAA 전까지만 지원함)
	 * field 이름은 종복 되면 안됨
	 * @param field
	 * @param header
	 * @throws SAXException
	 */
	public void addHeader(String field, String header) throws SAXException {
		if (topCommentSize == 0) {
			this.addHeader(field, header, DEFAULT_ALIGN);
		} else {
			addHeaderData(field, header, DEFAULT_ALIGN, null, null, false);
		}
	}

	public void addHeader(String field, String header, String align) throws SAXException {
		if (topCommentSize == 0) {
			String col = getNextCol();
			headers.put(field, new HeaderCell(col, field, header, ExcelUtils.toColIndex(col) - 1, align, 1, 1 , 1, null, null, false, false, false) );
		} else {
			addHeaderData(field, header, align, null, null, false);
		}
	}

	public void addHeader(String field, String header, String align, String codeName) throws SAXException {
		if (topCommentSize == 0) {
			String col = getNextCol();
			headers.put(field, new HeaderCell(col, field, header, ExcelUtils.toColIndex(col) - 1, align, 1, 1 , 1, codeName, null, false, false, false) );
		} else {
			addHeaderData(field, header, align, codeName, null, false);
		}

	}

	public void addHeader(String field, String header, String align, String codeName, String formula) throws SAXException {
		if (topCommentSize == 0) {
			String col = getNextCol();
			headers.put(field, new HeaderCell(col, field, header, ExcelUtils.toColIndex(col) - 1, align, 1, 1 , 1, codeName, formula, false, false, false) );
		} else {
			addHeaderData(field, header, align, codeName, null, false);
		}
	}

	public void addHeaderFormula(String field, String header, String formula) throws SAXException {
		if (topCommentSize == 0) {
			String col = getNextCol();
			headers.put(field, new HeaderCell(col, field, header, ExcelUtils.toColIndex(col) - 1, "right", 1, 1 , 1, null, formula, false, false, false) );
		} else {
			addHeaderData(field, header, DEFAULT_ALIGN, null, formula, false);
		}
	}

	public void addHeaderRequired(String field, String header, String formula) throws SAXException {
		if (topCommentSize == 0) {
			String col = getNextCol();
			headers.put(field, new HeaderCell(col, field, header, ExcelUtils.toColIndex(col) - 1, "right", 1, 1 , 1, null, formula, false, false, false) );
		} else {
			addHeaderData(field, header, "right", null, formula, false);
		}
	}

	public void addHeaderColor(String field, String header, boolean isRed) throws SAXException {
		if (topCommentSize == 0) {
			String col = getNextCol();
			headers.put(field, new HeaderCell(col, field, header, ExcelUtils.toColIndex(col) - 1, DEFAULT_ALIGN, 1, 1 , 1, null, null, isRed, false, false) );
		} else {
			addHeaderData(field, header, DEFAULT_ALIGN, null, null, isRed);
		}
	}

	public void addHeaderColor(String field, String header, boolean isRed, String formula) throws SAXException {
		if (topCommentSize == 0) {
			String col = getNextCol();
			headers.put(field, new HeaderCell(col, field, header, ExcelUtils.toColIndex(col) - 1, DEFAULT_ALIGN, 1, 1 , 1, null, formula, isRed, false, false) );
		} else {
			addHeaderData(field, header, DEFAULT_ALIGN, null, formula, isRed);
		}
	}

	public void addHeaderColor(String field, String header, String align, boolean isRed) throws SAXException {
		if (topCommentSize == 0) {
			String col = getNextCol();
			headers.put(field, new HeaderCell(col, field, header, ExcelUtils.toColIndex(col) - 1, align, 1, 1 , 1, null, null, isRed, false, false) );
		} else {
			addHeaderData(field, header, align, null, null, isRed);
		}
	}

	public void addHeaderColor(String field, String header, String align, boolean isRed, String formula) throws SAXException {
		if (topCommentSize == 0) {
			String col = getNextCol();
			headers.put(field, new HeaderCell(col, field, header, ExcelUtils.toColIndex(col) - 1, align, 1, 1 , 1, null, formula, isRed, false, false) );
		} else {
			addHeaderData(field, header, align, null, formula, isRed);
		}
	}

	public void addHeaderColor(String field, String header, String align, String codeName, boolean isRed) throws SAXException {
		if (topCommentSize == 0) {
			String col = getNextCol();
			headers.put(field, new HeaderCell(col, field, header, ExcelUtils.toColIndex(col) - 1, align, 1, 1 , 1, codeName, null, isRed, false, false) );
		} else {
			addHeaderData(field, header, align, codeName, null, isRed);
		}
	}

	public void addHeaderColor(String field, String header, String align, String codeName, boolean isRed, boolean isHeaderCode) throws SAXException {
		if (topCommentSize == 0) {
			String col = getNextCol();
			headers.put(field, new HeaderCell(col, field, header, ExcelUtils.toColIndex(col) - 1, align, 1, 1 , 1, codeName, null, isRed, false, isHeaderCode) );
		} else {
			addHeaderData(field, header, align, codeName, null, isRed, isHeaderCode);
		}
	}

	private void addHeaderData(String field, String name, String align, String codeName, String formula, boolean isRed) throws SAXException {

		String col = getNextCol();
		if(headers.size() == 0) {
			col = "A";
		}

		if (topCommentSize > 0) {
			ListOrderedMap<String, HeaderCell> rowHeaders = null;
			int rowIdx = doubleHeaders.size();
			if (rowIdx > topCommentSize) {
				rowHeaders = doubleHeaders.get(rowIdx -1);
			}

			if (rowHeaders == null) {
				rowHeaders = new ListOrderedMap<String, HeaderCell>();
				doubleHeaders.add(rowHeaders);
				rowIdx++;
			}

			int colIdx = ExcelUtils.toColIndex(col) - 1;
			rowHeaders.put(col, new HeaderCell(col, field, name, colIdx , align, rowIdx, 1 , 1, codeName, formula, isRed, false, false));
			headers.put(field, new HeaderCell(col, field, name, ExcelUtils.toColIndex(col) - 1, align, rowIdx, 1 , 1, codeName, formula, isRed, false, false) );

		} else {
			headers.put(field, new HeaderCell(col, field, name, ExcelUtils.toColIndex(col) - 1, align, 1, 1 , 1, codeName, formula, isRed, false, false) );
		}
	}

	private void addHeaderData(String field, String name, String align, String codeName, String formula, boolean isRed, boolean isHeaderCode) throws SAXException {

		String col = getNextCol();
		if(headers.size() == 0) {
			col = "A";
		}

		if (topCommentSize > 0) {
			ListOrderedMap<String, HeaderCell> rowHeaders = null;
			int rowIdx = doubleHeaders.size();
			if (rowIdx > topCommentSize) {
				rowHeaders = doubleHeaders.get(rowIdx -1);
			}

			if (rowHeaders == null) {
				rowHeaders = new ListOrderedMap<String, HeaderCell>();
				doubleHeaders.add(rowHeaders);
				rowIdx++;
			}

			int colIdx = ExcelUtils.toColIndex(col) - 1;
			rowHeaders.put(col, new HeaderCell(col, field, name, colIdx , align, rowIdx, 1 , 1, codeName, formula, isRed, false, isHeaderCode));
			headers.put(field, new HeaderCell(col, field, name, ExcelUtils.toColIndex(col) - 1, align, rowIdx, 1 , 1, codeName, formula, isRed, false, isHeaderCode) );

		} else {
			headers.put(field, new HeaderCell(col, field, name, ExcelUtils.toColIndex(col) - 1, align, 1, 1 , 1, codeName, formula, isRed, false, isHeaderCode) );
		}
	}

	public void addTopComment(int rowIdx, String comment) throws SAXException {
		addDoubleHeader(rowIdx, null, comment, 1, "A", 1, "left", null, false, true);
		topCommentSize++;
	}
	public void addTopComment(int rowIdx, String col, String comment) throws SAXException {
		addDoubleHeader(rowIdx, null, comment, 1, col, 1, "left", null, false, true);
		topCommentSize++;
	}

	public void addDoubleHeader(int rowIdx, String field, String header, int rowspan, String col, int colspan) throws SAXException {
		addDoubleHeader(rowIdx, field, header, rowspan, col, colspan, DEFAULT_ALIGN, null, false, false);
	}

	public void addDoubleHeader(int rowIdx, String field, String header, int rowspan, String col, int colspan, String align) throws SAXException {
		addDoubleHeader(rowIdx, field, header, rowspan, col, colspan, align, null, false, false);
	}

	public void addDoubleHeader(int rowIdx, String field, String header,int rowspan, String col, int colspan, String align, String codeName) throws SAXException {
		addDoubleHeader(rowIdx, field, header, rowspan, col, colspan, align, codeName, false, false);
	}

	public void addDoubleHeaderColor(int rowIdx, String field, String header, int rowspan, String col, int colspan, boolean isRed) throws SAXException {
		addDoubleHeader(rowIdx, field, header, rowspan, col, colspan, DEFAULT_ALIGN, null, isRed, false);
	}

	public void addDoubleHeaderColor(int rowIdx, String field, String header, int rowspan, String col, int colspan, String align, boolean isRed) throws SAXException {
		addDoubleHeader(rowIdx, field, header, rowspan, col, colspan, align, null, isRed, false);
	}

	public void addDoubleHeaderColor(int rowIdx, String field, String header,int rowspan, String col, int colspan, String align, String codeName, boolean isRed) throws SAXException {
		addDoubleHeader(rowIdx, field, header, rowspan, col, colspan, align, codeName, isRed, false);
	}

	public void addDoubleHeader(int rowIdx, String field, String header,int rowspan, String col, int colspan, String align, String codeName, boolean isRed, boolean isComment) throws SAXException {
		ListOrderedMap<String, HeaderCell> rowHeaders = null;
		if (doubleHeaders.size() >= rowIdx)
			rowHeaders = doubleHeaders.get(rowIdx-1);

		if (rowHeaders == null) {
			while(doubleHeaders.size() < rowIdx) {
				rowHeaders = new ListOrderedMap<String, HeaderCell>();
				doubleHeaders.add(rowHeaders);
			}
		}

		int colIdx = ExcelUtils.toColIndex(col) - 1;
		if (rowspan < 1) rowspan = 1;
		if (colspan < 1) colspan = 1;
		rowHeaders.put(col, new HeaderCell(col, field, header, colIdx , align, rowIdx, rowspan , colspan, codeName, null, isRed, isComment, false));

		// field가 null이 존재하므로 col을 key로 사용함
		if (!ObjectUtil.isEmpty(field)) {
			String col1 = getNextCol();
			headers.put(field, new HeaderCell(col1, field, header, ExcelUtils.toColIndex(col1) - 1, align, 1, 1 , 1, null, null, isRed, false, false) );
		}
	}

	private String getNextCol() {
		String col = "";
		int colSize = 0;

		final char ASCII_ALPHABAT_A = 65;
//		final char ASCII_ALPHABAT_z = 90;
		final int ASCII_ALPHABAT_SIZE = 26;

		if(headers.size() > ASCII_ALPHABAT_SIZE) {
			int addGrupSize = (int)Math.floor(headers.size() / ASCII_ALPHABAT_SIZE) - 1;
			char bString = ASCII_ALPHABAT_A;
			bString += (char)addGrupSize;

			col = String.valueOf(bString);
			colSize = headers.size() % ASCII_ALPHABAT_SIZE;
		} else {
			colSize = headers.size();
		}

		// 아스키 코드 알파벳 시작 문자열 : A
        char aString = ASCII_ALPHABAT_A;
        aString += colSize;
        col += String.valueOf(aString) ;

        return col;
	}

	public void addDictHeader(String col, String field, String header) throws SAXException {
		headers.put(field, new HeaderCell(col, field, header, ExcelUtils.toColIndex(col) - 1, DEFAULT_ALIGN, 1, 1 , 1, null, null, false, false, false) );
	}

	public void addDictHeader(String col, String field, String header, String align) throws SAXException {
		headers.put(field, new HeaderCell(col, field, header, ExcelUtils.toColIndex(col) - 1, align, 1, 1 , 1, null, null, false, false, false) );
	}

	public void addComment(String comment) throws SAXException {
		comments.add(comment);
	}

}
