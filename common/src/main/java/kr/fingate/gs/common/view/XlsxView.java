package kr.fingate.gs.common.view;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.fingate.gs.common.consts.CommonConst;
import kr.fingate.gs.common.external.excel.ExcelExportModel;
import kr.fingate.gs.common.external.excel.ExcelMap;
import kr.fingate.gs.common.external.excel.event.mapping.BeanToSheetUtils;
import kr.fingate.gs.common.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.LocalizedResourceHelper;
import org.springframework.mail.javamail.ConfigurableMimeFileTypeMap;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.AbstractView;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 * ExcelMap을 사용한 Data 조회 후 Excel 생성
 */
@Slf4j
@Component("xlsxView")
public class XlsxView extends AbstractView {

    public static final String CONTENT_TYPE = "application/vnd.ms-excel; charset=UTF-8";
    ConfigurableMimeFileTypeMap mimeMap = null;
    private static final String EXTENSION = ".xlsx";
    private boolean isUseTemplete = false;

    CellStyle headerStyle;
    CellStyle bodyStyle;

    public XlsxView() {
        setContentType(CONTENT_TYPE);
    }

    @Override
    protected boolean generatesDownloadContent() {
        return true;
    }

    @Override
    protected final void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Resource resource = new ClassPathResource("/mimetype/mime.types", getClass());
        mimeMap = new ConfigurableMimeFileTypeMap();
        mimeMap.setMappingLocation(resource);
        mimeMap.afterPropertiesSet();

        String fileName = (model.containsKey("fileName") ? (String) model.get("fileName") : "downfile");
        String userAgent = request.getHeader("User-Agent");

        if (!fileName.endsWith(EXTENSION)) fileName += EXTENSION;
        fileName = HttpUtil.getEncodingFilename(fileName, userAgent);

        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setContentType(StringUtils.defaultIfEmpty(mimeMap.getContentType(fileName), CONTENT_TYPE));

        SXSSFWorkbook workbook = new SXSSFWorkbook();
        if (model.containsKey("workbook")) {
            workbook = (SXSSFWorkbook) model.get("workbook");
        } else if (model.containsKey("sheet")) {
            if ((model.get("sheet")) instanceof List) {
                Map<String, Object> code = null;
                if (model.containsKey(CommonConst.CODE)) {
                    code = (Map<String, Object>) model.get(CommonConst.CODE);
                }

                List<ExcelExportModel> sheetList = (List<ExcelExportModel>) model.get("sheet");
                BeanToSheetUtils.mapBeanToSheet(workbook, sheetList, code);
            } else {
                Map<String, Object> code = null;
                if (model.containsKey(CommonConst.CODE)) {
                    code = (Map<String, Object>) model.get(CommonConst.CODE);
                }

                ExcelExportModel<T> bean = (ExcelExportModel<T>) model.get("sheet");
                BeanToSheetUtils.mapBeanToSheet(workbook, bean, code);
            }
        } else {
            buildExcelDocument(model, workbook, request, response);
        }

        log.debug("Created Excel Workbook from scratch");
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
        out.close();

        workbook.dispose();
    }

    @SuppressWarnings("unchecked")
    protected void buildExcelDocument(Map<String, Object> model, SXSSFWorkbook wb, HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (model.isEmpty() || !model.containsKey(CommonConst.LIST)) {
            throw new Exception("Unable to find an Object with name 'list'");
        }

        // templateSource 지정시
        if (model.containsKey("templateSource")) {
            wb = this.getTemplateSource((String) model.get("templateSource"), request);
            isUseTemplete = true;
        }

        int count = 0;
        int begin = model.containsKey("begin") ? Integer.parseInt((String) model.get("begin")) : 1;

        getHeaderCellStyle(wb);
        getBodyCellStyle(wb);

        if ((model.get(CommonConst.LIST)) instanceof Map) {
            // Map인 경우 여러개의 sheet에 쓰기
            log.debug("Process multiple sheet");
            count = this.appendToExcelDocument(wb, (Map<String, Object>) model.get(CommonConst.LIST), begin);

        } else {
            // List인 경우 한개의 sheet에 쓰기
            log.debug("Process one sheet");

            if (model.containsKey("excelHeader")) {
                int cellBegin = 0;
                if (model.containsKey("headCellBegin")) {
                    cellBegin = (int) model.get("headCellBegin");
                }
                count = this.appendToExcelHeader(wb, (List<Object>) model.get("excelHeader"), cellBegin);
            }

            if (begin < count) {
                begin = count;
            }

            count = this.appendToExcelDocument(wb, (List<ExcelMap>) model.get(CommonConst.LIST), begin);
        }

        response.setContentType(getContentType());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        wb.write(baos);
        baos.close();
        wb.dispose();

        ServletOutputStream out = response.getOutputStream();
        out.write(baos.toByteArray());
        out.flush();
        out.close();
    }

    protected SXSSFWorkbook getTemplateSource(String url, HttpServletRequest request) throws Exception {
        LocalizedResourceHelper helper = new LocalizedResourceHelper(getApplicationContext());
        Resource inputFile = helper.findLocalizedResource(url, EXTENSION, RequestContextUtils.getLocale(request));

        // Create the Excel document from the source.
//		if (log.isDebugEnabled()) {
//			log.debug("Loading Excel workbook from " + inputFile);
//		}
        OPCPackage pkg = OPCPackage.open(inputFile.getInputStream());
        return new SXSSFWorkbook(new XSSFWorkbook(pkg));
    }


    private Cell creatCell(Row row, int cellIdx, Object obj) {
        Cell cell = row.createCell(cellIdx);

        if (obj instanceof Date) cell.setCellValue((Date) obj);
        else if (obj instanceof Boolean) cell.setCellValue((Boolean) obj);
        else if (obj instanceof Integer) cell.setCellValue((Integer) obj);
        else if (obj instanceof Long) cell.setCellValue((Long) obj);
        else if (obj instanceof Double) cell.setCellValue((Double) obj);
        else if (obj instanceof BigDecimal) cell.setCellValue(((BigDecimal) obj).doubleValue());
        else cell.setCellValue((String) obj);

        return cell;
    }

    /**
     * 지정된 엑셀 템플릿을 이용하여 첫번째 시트에 2열부터 기록
     * @param wb
     * @param list
     * @param begin
     * @return
     * @throws Exception
     */
    private int appendToExcelDocument(SXSSFWorkbook wb, List<ExcelMap> list, int begin) throws Exception {
        log.debug("Writing on existing file...");
        int count = 0;

        SXSSFSheet sheet = wb.getSheetAt(0);
        if (sheet == null)
            sheet = wb.createSheet();

        //((SXSSFSheet) sheet).setRandomAccessWindowSize(100000);
        int cellIdx = 0;
        for (int i = 0; i < list.size(); i++) {
            Row row = sheet.createRow(i + begin);
            ExcelMap map = list.get(i);
            cellIdx = 0;
            for (Object key : map.keySet()) {
                Cell cell = creatCell(row, cellIdx, map.get(key));
                cell.setCellStyle(bodyStyle);
                cellIdx++;
            }
        }
        log.debug("Processed {} rows", list.size());
        count = list.size();
        return count;
    }


    /**
     * 여러개의 sheet 생성이 필요한 경우 sheet를 생성해서 엑셀 만듬
     * @param wb
     * @param data
     * @param begin
     * @return
     * @throws Exception
     */
    private int appendToExcelDocument(SXSSFWorkbook wb, Map<String, Object> data, int begin) throws Exception {
        log.debug("Writing on existing file(multiple sheet)..");
        int count = 0;

        int sheetIndex = 0;
        int cellIdx = 0;

        for (Entry<String, Object> entry : data.entrySet()) {
            String sheetName = entry.getKey();
            @SuppressWarnings("unchecked")
            List<ExcelMap> list = (List<ExcelMap>) entry.getValue();

            SXSSFSheet sheet = null;
            if (isUseTemplete) sheet = wb.getSheetAt(sheetIndex);
            else sheet = wb.createSheet();

            //((SXSSFSheet) sheet).setRandomAccessWindowSize(100000);
            wb.setSheetName(wb.getSheetIndex(sheet), sheetName);
            for (int i = 0; i < list.size(); i++) {
                Row row = sheet.createRow(i + begin);
                ExcelMap map = list.get(i);
                cellIdx = 0;
                for (Object key : map.keySet()) {
                    Cell cell = creatCell(row, cellIdx, map.get(key));
                    if (i == 0 && begin == 0) {
                        cell.setCellStyle(headerStyle);
                    } else {
                        cell.setCellStyle(bodyStyle);
                    }
                    if (i == 0 && !isUseTemplete) {
                        sheet.autoSizeColumn(cellIdx);
                    }
                    cellIdx++;
                }
            }
            sheetIndex++;
            log.debug("Processed {} rows in sheet" + sheetIndex + "(" + sheet.getSheetName() + ")", list.size());
            count += list.size();
        }
        return count;
    }

    /**
     * 단건의 sheet를 사용하는 경우 데이터 List를 가져와 한개의 sheet만 생성 후 엑셀을 만듬
     *
     * @param wb
     * @param data
     * @param cellBegin
     * @return
     */
    private int appendToExcelHeader(SXSSFWorkbook wb, List<Object> data, int cellBegin) {
        log.debug("Writing on header..");

        int sheetIndex = 0;
        int begin = 0;

        SXSSFSheet sheet = null;
        if (isUseTemplete)
            sheet = wb.getSheetAt(sheetIndex);
        else sheet = wb.createSheet();

        //((SXSSFSheet) sheet).setRandomAccessWindowSize(100000);
        for (Object list : data) {
            Row row = null;
            if (sheet.getRow(begin) == null) {
                row = sheet.createRow(begin);
            } else {
                row = sheet.getRow(begin);
            }

            int cellIdx = cellBegin;
            for (ExcelMap map : (List<ExcelMap>) list) {
                String title = (String) map.get("title");
                //String styleYn =((String) map.get("style"));
                int rowspan = map.get("rowspan") == null ? 0 : ((Integer) map.get("rowspan")).intValue() - 1;
                int colspan = map.get("colspan") == null ? 0 : ((Integer) map.get("colspan")).intValue() - 1;

                CellRangeAddress cellRangeAddress = new CellRangeAddress(begin, begin + rowspan, cellIdx, cellIdx + colspan);

                if (rowspan != 0 || colspan != 0) {
                    log.debug("MERGE CELL [" + title + "] : row " + begin + " - " + (begin + rowspan) + " / col " + cellIdx + " - " + (cellIdx + colspan));
                    SXSSFRow tempRow = null;
                    Cell tempCell = null;
                    for (int temprow = begin + 1; temprow <= (begin + rowspan); temprow++) {
                        if (sheet.getRow(temprow) == null) {
                            tempRow = sheet.createRow(temprow);
                            tempCell = tempRow.createCell(cellIdx);
                            tempCell.setCellStyle(wb.getCellStyleAt(1));
                        } else {
                            tempRow = sheet.getRow(temprow);
                            if (tempRow.getCell(cellIdx) == null) {
                                tempCell = tempRow.createCell(cellIdx);
                                tempCell.setCellStyle(wb.getCellStyleAt(1));
                            }
                        }
                    }
                    for (int tempcell = cellIdx + 1; tempcell <= (cellIdx + colspan); tempcell++) {
                        if (row.getCell(tempcell) == null) {
                            tempCell = row.createCell(tempcell);
                            tempCell.setCellStyle(wb.getCellStyleAt(1));
                        }
                    }
                    sheet.addMergedRegion(cellRangeAddress);
                }
                //creatCell(row, cellIdx, title);
                Cell cell = null;
                if (row.getCell(cellIdx) == null) {
                    cell = row.createCell(cellIdx);
                } else {
                    cell = row.getCell(cellIdx);
                }
                cell.setCellValue(title);

                for (int colNum = cellRangeAddress.getFirstColumn(); colNum <= cellRangeAddress.getLastColumn(); colNum++) {
                    Cell currentCell = row.getCell(colNum);
                    if (currentCell == null) {
                        currentCell = row.createCell(colNum);
                        log.trace("while check cell " + row + ":" + colNum + " was created");
                    }
                    currentCell.setCellStyle(headerStyle);
                }

                cellIdx = cellIdx + colspan;
                cellIdx++;
            }

            begin++;
        }

        return begin;
    }

    private void getHeaderCellStyle(SXSSFWorkbook xworkbook) {
        // header
        Font headerFont = xworkbook.createFont();
        headerFont.setFontName("맑은 고딕");
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 10);


        CellStyle HeaderStyle = xworkbook.createCellStyle();
        HeaderStyle.setAlignment(HorizontalAlignment.CENTER);    // 가운데
        HeaderStyle.setFillForegroundColor(HSSFColorPredefined.GREY_25_PERCENT.getIndex()); // 배경색
        HeaderStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        HeaderStyle.setBorderTop(BorderStyle.THIN);     // 테두리
        HeaderStyle.setBorderRight(BorderStyle.THIN);
        HeaderStyle.setBorderBottom(BorderStyle.THIN);
        HeaderStyle.setBorderLeft(BorderStyle.THIN);

        HeaderStyle.setFont(headerFont);

        headerStyle = HeaderStyle;
    }

    private void getBodyCellStyle(SXSSFWorkbook xworkbook) {
        Font bodyFont = xworkbook.createFont();
        bodyFont.setFontName("맑은 고딕");
        bodyFont.setFontHeightInPoints((short) 10);

        CellStyle BodyCenterStyle = xworkbook.createCellStyle();
        //BodyCenterStyle.setAlignment(HorizontalAlignment.CENTER);
        BodyCenterStyle.setBorderTop(BorderStyle.THIN); // 테두리
        BodyCenterStyle.setBorderRight(BorderStyle.THIN);
        BodyCenterStyle.setBorderBottom(BorderStyle.THIN);
        BodyCenterStyle.setBorderLeft(BorderStyle.THIN);

        BodyCenterStyle.setFont(bodyFont);

        bodyStyle = BodyCenterStyle;
    }

}
