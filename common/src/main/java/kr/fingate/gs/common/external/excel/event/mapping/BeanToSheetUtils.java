package kr.fingate.gs.common.external.excel.event.mapping;

import kr.fingate.gs.common.external.excel.ExcelExportModel;
import kr.fingate.gs.common.util.ObjectUtil;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class BeanToSheetUtils {

	protected static final Logger logger = LoggerFactory.getLogger(BeanToSheetUtils.class);

	public static <T> void mapBeanToSheet(ExcelExportModel<T> bean) throws Exception {
		SXSSFWorkbook workbook = new SXSSFWorkbook();
		mapBeanToSheet(workbook, bean, null);
	}
	// workbook의 경로와 bean의 인스턴스를 전달하면 지정된 시트의 값을 eventmodel을 사용(메모리 사용량이 적음)하여 bean에 설정하고 반환합니다.
	@SuppressWarnings("unchecked")
	public static <T> void mapBeanToSheet(SXSSFWorkbook workbook, ExcelExportModel<T> bean) throws Exception {
		mapBeanToSheet(workbook, bean, null);
	}

	// workbook의 경로와 bean의 인스턴스를 전달하면 지정된 시트의 값을 eventmodel을 사용(메모리 사용량이 적음)하여 bean에 설정하고 반환합니다.
	@SuppressWarnings("unchecked")
	public static <T> void mapBeanToSheet(SXSSFWorkbook workbook, List<ExcelExportModel> beanList) throws Exception {
		BeanToSheetUtils.setWorkbook(workbook);
		int idx = 1;
		for(ExcelExportModel<T> bean : beanList) {
			if(ObjectUtil.isEmpty(bean.getSheetName())) {
				bean.setSheetName("Sheet" + idx);
				idx++;
			}

			mapBeanToSheet(workbook, bean, null);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> void mapBeanToSheet(SXSSFWorkbook workbook, List<ExcelExportModel> beanList, Map<String,Object> codes) throws Exception {
		BeanToSheetUtils.setWorkbook(workbook);
		int idx = 1;
		for(ExcelExportModel<T> bean : beanList) {
			if(ObjectUtil.isEmpty(bean.getSheetName())) {
				bean.setSheetName("Sheet" + idx);
				idx++;
			}

			mapBeanToSheet(workbook, bean, codes);
		}
	}

	// workbook의 경로와 bean의 인스턴스를 전달하면 지정된 시트의 값을 eventmodel을 사용(메모리 사용량이 적음)하여 bean에 설정하고 반환합니다.
	@SuppressWarnings("unchecked")
	public static <T> void mapBeanToSheet(SXSSFWorkbook workbook, ExcelExportModel<T> bean, Map<String,Object> codes) throws Exception {
		BeanToSheetUtils.setWorkbook(workbook);
		BeanToSheetMapper mapper = new BeanToSheetMapper(workbook);
		if(ObjectUtil.isEmpty(bean.getSheetName())) {
			bean.setSheetName("Sheet1");
		}

		mapper.setExcelCodes(codes);
		mapper.execute(bean);
	}


	public static void setWorkbook(SXSSFWorkbook xworkbook) {

		if(xworkbook.getCellStyleAt(1) != null) return;

		// header
		Font headerFont = xworkbook.createFont();
		headerFont.setFontName("맑은 고딕");
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short)10);


		CellStyle HeaderStyle = xworkbook.createCellStyle();
		HeaderStyle.setAlignment(HorizontalAlignment.CENTER);	// 가운데
		HeaderStyle.setFillForegroundColor(HSSFColorPredefined.GREY_25_PERCENT.getIndex()); // 배경색
		HeaderStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		HeaderStyle.setBorderBottom(BorderStyle.THIN); // 테두리
		HeaderStyle.setBorderTop(BorderStyle.THIN);
		HeaderStyle.setBorderLeft(BorderStyle.THIN);
		HeaderStyle.setBorderRight(BorderStyle.THIN);
		HeaderStyle.setFont(headerFont);

		// body
		Font bodyFont = xworkbook.createFont();
		bodyFont.setFontName("맑은 고딕");
		bodyFont.setFontHeightInPoints((short)10);

		CellStyle BodyCenterStyle = xworkbook.createCellStyle();
		BodyCenterStyle.setAlignment(HorizontalAlignment.CENTER);
		BodyCenterStyle.setBorderBottom(BorderStyle.THIN); // 테두리
		BodyCenterStyle.setBorderTop(BorderStyle.THIN);
		BodyCenterStyle.setBorderLeft(BorderStyle.THIN);
		BodyCenterStyle.setBorderRight(BorderStyle.THIN);
		BodyCenterStyle.setFont(bodyFont);

		CellStyle BodyLeftStyle = xworkbook.createCellStyle();
		BodyLeftStyle.setAlignment(HorizontalAlignment.LEFT);
		BodyLeftStyle.setBorderBottom(BorderStyle.THIN); // 테두리
		BodyLeftStyle.setBorderTop(BorderStyle.THIN);
		BodyLeftStyle.setBorderLeft(BorderStyle.THIN);
		BodyLeftStyle.setBorderRight(BorderStyle.THIN);
		BodyLeftStyle.setFont(bodyFont);

		CellStyle BodyRightStyle = xworkbook.createCellStyle();
		BodyRightStyle.setAlignment(HorizontalAlignment.RIGHT);
		BodyRightStyle.setBorderBottom(BorderStyle.THIN); // 테두리
		BodyRightStyle.setBorderTop(BorderStyle.THIN);
		BodyRightStyle.setBorderLeft(BorderStyle.THIN);
		BodyRightStyle.setBorderRight(BorderStyle.THIN);
		BodyRightStyle.setFont(bodyFont);

		// header 붉은색 (필수값) 표기
		Font headerFontRed = xworkbook.createFont();
		headerFontRed.setFontName("맑은 고딕");
		headerFontRed.setBold(true);
		headerFontRed.setColor(IndexedColors.RED.getIndex());
		headerFontRed.setFontHeightInPoints((short)10);

		CellStyle HeaderRedStyle = xworkbook.createCellStyle();
		HeaderRedStyle.setAlignment(HorizontalAlignment.CENTER);	// 가운데
		HeaderRedStyle.setFillForegroundColor(HSSFColorPredefined.GREY_25_PERCENT.getIndex()); // 배경색
		HeaderRedStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		HeaderRedStyle.setBorderBottom(BorderStyle.THIN); // 테두리
		HeaderRedStyle.setBorderTop(BorderStyle.THIN);
		HeaderRedStyle.setBorderLeft(BorderStyle.THIN);
		HeaderRedStyle.setBorderRight(BorderStyle.THIN);
		HeaderRedStyle.setFont(headerFontRed);
		
		// 코멘트 설정
		CellStyle CommentStyle = xworkbook.createCellStyle();
		CommentStyle.setAlignment(HorizontalAlignment.LEFT);	//왼쪽 정렬
		CommentStyle.setFont(bodyFont);
	}

}
