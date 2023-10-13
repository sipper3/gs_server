package kr.fingate.gs.common.external.excel.event.mapping;

import kr.fingate.gs.common.external.excel.event.SheetHandler;
import kr.fingate.gs.common.external.excel.event.WorkbookHandler;
import kr.fingate.gs.common.external.excel.event.mapping.annotation.Sheet;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFReader.SheetIterator;
import org.apache.poi.xssf.model.SharedStringsTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;

public class SheetToBeanMapper {

	protected static final Logger logger = LoggerFactory.getLogger(SheetToBeanMapper.class);

	// 값을 설정하는 대상 bean
	private Object bean = null;
	// 통합 문서 경로
	private String filePath = null;
	// 처리대상 시트명
	private String sheetName = Sheet.DFAULT_NAME;
	// 처리대상 시트 인덱스
	private int sheetIndex = Sheet.DEFAULT_INDEX;

	public SheetToBeanMapper(String filePath, Object bean) throws Exception {
		this.filePath = filePath;
		this.bean = bean;
		Sheet sheet = bean.getClass().getAnnotation(Sheet.class);
		if (sheet == null) {
			throw new IllegalArgumentException("클래스에 @Sheet annotation이 존재하지 않습니다.");
		}
		this.sheetName = sheet.name();
		this.sheetIndex = sheet.index();
	}

	// 매핑 처리 시작
	public void execute() throws Exception {

		OPCPackage pkg = OPCPackage.open(filePath);
		XSSFReader reader = new XSSFReader(pkg);
		SAXParserFactory spf = SAXParserFactory.newInstance();

		// 시트 인덱스로 처리
		if(sheetIndex > -1) {
			// 워크 시트에서 값을 검색
			SharedStringsTable sst = reader.getSharedStringsTable();
			SAXParser saxParser = spf.newSAXParser();
			XMLReader parser = saxParser.getXMLReader();
			SheetHandler handler = new SheetHandler(sst, new FieldValueArranger(bean));
			parser.setContentHandler(handler);

			SheetIterator iter = (SheetIterator) reader.getSheetsData();
			int index = 0;
	        while (iter.hasNext()) {
	        	// 최대 10개의 시트까지만 처리(엑셀문서에 빈 시트가 있을경우 무한루프가 발생하는 경우도 있음)
	        	if(index > 9) break;

	        	if(sheetIndex == index) {
	        		InputStream targetSheet = iter.next();
	        		InputSource sheetSource = new InputSource(targetSheet);
	    			// 시트의 내용을 해석하면서 bean에 매핑
	    			parser.parse(sheetSource);
	    			targetSheet.close();
	    			break;
	        	}
	        	++index;
	        }

		// 시트명으로 처리
		} else {
			// workbook에서 대상 시트 rId를 얻음
			SAXParser saxParser = spf.newSAXParser();
			XMLReader wbParser = saxParser.getXMLReader();
			WorkbookHandler wbHandler = new WorkbookHandler(sheetName);
			wbParser.setContentHandler(wbHandler);
			// workbook를 얻음
			InputSource wbSource = new InputSource(reader.getWorkbookData());
			wbParser.parse(wbSource);
			// 지정 시트 이름에 해당하는 rId을 취득
			String rId = wbHandler.getrId();
			if (rId == null) {
				throw new Exception("대상 시트가 없습니다. 시트 이름 = " + sheetName);
			}

			// 워크 시트에서 값을 검색
			SharedStringsTable sst = reader.getSharedStringsTable();
			SAXParser saxParser2 = spf.newSAXParser();
			XMLReader parser = saxParser2.getXMLReader();
			SheetHandler handler = new SheetHandler(sst, new FieldValueArranger(bean));
			parser.setContentHandler(handler);

			// 시트를 지정하여 검색
			InputStream targetSheet = reader.getSheet(rId);
			InputSource sheetSource = new InputSource(targetSheet);
			// 시트의 내용을 해석하면서 bean에 매핑
			parser.parse(sheetSource);
			targetSheet.close();
		}
	}

}
