package kr.fingate.gs.common.external.excel.event.util;

import kr.fingate.gs.common.external.excel.ExcelExportModel;
import kr.fingate.gs.common.external.excel.event.mapping.BeanToSheetUtils;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.ConfigurableMimeFileTypeMap;
import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ExcelUtils {

	public static final String STRING_TRUE = "1";

	private ExcelUtils() throws Exception {
	}

	// 날짜 형식으로 값을 취득
	public static Date getDateValue(String value) throws SAXException {
		if (value == null) {
			return null;
		}
		Double dblVal = null;
		try {
			dblVal = getDoubleValue(value);
		} catch (Exception e) {
			throw new IllegalStateException("날짜로 변환 할 수 없습니다. value = " + value, e);
		}
		return DateUtil.getJavaDate(dblVal.doubleValue());
	}

	// 숫자 형식으로 값을 취득
	public static Integer getIntegerValue(String value) throws SAXException {
		if (value == null) {
			return null;
		}
		Double dblVal = getDoubleValue(value);
		return Integer.valueOf(dblVal.intValue());
	}

	// 숫자 형식으로 값을 취득
	public static Long getLongValue(String value) throws SAXException {
		if (value == null) {
			return null;
		}
		Double dblVal = getDoubleValue(value);
		return Long.valueOf(dblVal.longValue());
	}

	// 숫자 형식으로 값을 취득
	public static Short getShortValue(String value) throws SAXException {
		if (value == null) {
			return null;
		}
		Double dblVal = getDoubleValue(value);
		return Short.valueOf(dblVal.shortValue());
	}

	// 숫자 형식으로 값을 취득
	public static Double getDoubleValue(String value) throws SAXException {
		if (value == null) {
			return null;
		}
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException e) {
			throw new IllegalStateException("숫자로 변환 할 수 없습니다. value = " + value, e);
		}
	}

	// 부울 형식으로 값을 취득
	public static Boolean getBooleanValue(String value) throws SAXException {
		return Boolean.valueOf(STRING_TRUE.equals(value));
	}

	// A1 : B1 등의 표기에서 첫 번째 컬럼 문자열을 추출
	public static String getFirstColString(String range) throws SAXException {
		char[] cs = range.toCharArray();
		String col = "";
		for (char c : cs) {
			if ('0' <= c && c <= '9') {
				return col;
			} else {
				col += c;
			}
		}
		return col;
	}

	// A1 표기에서 R1C1 표기의 열로 변환
	public static int toColIndex(String colString) throws SAXException {
		return CellReference.convertColStringToIndex(colString) + 1;
	}

	public static SXSSFWorkbook getWorkbook(ExcelExportModel sheet) throws Exception {
		return getWorkbook(sheet, null, ExcelUtils.class);
	}

	public static SXSSFWorkbook getWorkbook(ExcelExportModel sheet,
			Map code) throws Exception {
		return getWorkbook(sheet, code, ExcelUtils.class);
	}

	public static SXSSFWorkbook getWorkbook(ExcelExportModel sheet,
			Map code, Class<?> clazz) throws Exception {

		List<ExcelExportModel> sheetList = new ArrayList<ExcelExportModel>();
		sheetList.add(sheet);

		return getWorkbook(sheetList, code, clazz);
	}

	public static SXSSFWorkbook getWorkbook(List<ExcelExportModel> sheetList) throws Exception {
		return getWorkbook(sheetList, null, ExcelUtils.class);
	}

	public static SXSSFWorkbook getWorkbook(List<ExcelExportModel> sheetList,
			Map<String,Object> code) throws Exception {
		return getWorkbook(sheetList, code, ExcelUtils.class);
	}

	public static SXSSFWorkbook getWorkbook(List<ExcelExportModel> sheetList,
			Map<String,Object> code, Class<?> clazz) throws Exception {

		Resource resource = new ClassPathResource("/mimetype/mime.types", clazz);
		ConfigurableMimeFileTypeMap mimeMap = new ConfigurableMimeFileTypeMap();
		mimeMap.setMappingLocation(resource);
    	mimeMap.afterPropertiesSet();

        SXSSFWorkbook workbook = new SXSSFWorkbook();

        if(sheetList != null) {

        	if(sheetList.size() > 1) {

        		BeanToSheetUtils.mapBeanToSheet(workbook, sheetList, code);
        	} else {
        		ExcelExportModel<T> bean = sheetList.get(0);
        		BeanToSheetUtils.mapBeanToSheet(workbook, bean, code);
        	}
        }

		return workbook;
	}
}
