package kr.fingate.gs.common.external.excel.event.mapping;


import kr.fingate.gs.common.dto.code.CodeDto;
import kr.fingate.gs.common.external.excel.ExcelMap;
import kr.fingate.gs.common.external.excel.ExcelExportModel;
import kr.fingate.gs.common.external.excel.ExcelHeader;
import kr.fingate.gs.common.external.excel.ExcelHeader.HeaderCell;
import kr.fingate.gs.common.external.excel.event.mapping.annotation.ImportField;
import kr.fingate.gs.common.external.excel.event.mapping.annotation.ImportListField;
import kr.fingate.gs.common.external.excel.event.util.ExcelUtils;
import kr.fingate.gs.common.util.ObjectUtil;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections4.map.ListOrderedMap;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFDataValidationConstraint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.Pattern;

public class BeanToSheetMapper {

	protected static final Logger logger = LoggerFactory.getLogger(BeanToSheetMapper.class);

	// Excel
	private SXSSFWorkbook workbook = null;

	private List<ImportFieldInfo> fieldInfoList = new ArrayList<ImportFieldInfo>();

	private Map<String, Object> codes = null;

	private int DROPDOWN_START_ROW = 1;
	private int DROPDOWN_DEFAULT_ROW = 10;
	private int COMMENT_START_ROW = 15;



	public BeanToSheetMapper(SXSSFWorkbook workbook) throws Exception {
		this.workbook = workbook;
	}

	public void setExcelCodes(Map<String, Object> codes) {
		this.codes = codes;
	}


	// 매핑 처리 시작
	public <T> void execute(ExcelExportModel<T> bean) throws Exception {

		ExcelHeader excelHeader = null;
		ListOrderedMap<String, HeaderCell> headers = null;
		List<ListOrderedMap<String, HeaderCell>> doubleHeaders = null;
		List<String> comments = null;
		int fixRow = 0;
		int columnSize = 0;
		String sheetName = "";
		List<T> list = null;
		boolean annoList = false;
		int startRow = 1;

		Method[] methods = (bean.getClass()).getMethods();
		for (Method method : methods) {
			if (method.getName().equals("getList")) {
				list = (List<T>)method.invoke(bean);
			}
			else if (method.getName().equals("getHeader")) {
				excelHeader = (ExcelHeader) method.invoke(bean);
				headers = excelHeader.getHeaders();
				doubleHeaders = excelHeader.getDoubleHeaders();
				if (!ObjectUtil.isEmpty(doubleHeaders))
					startRow = doubleHeaders.size();
				comments = excelHeader.getComments();
			}
			else if (method.getName().equals("getFixRow")) {
				fixRow = (Integer) method.invoke(bean);
			}
			else if (method.getName().equals("getSheetName")) {
				sheetName = (String) method.invoke(bean);
			}
		}

		if(headers == null || headers.size() == 0) {
			if(bean.getType() != null) {
				Field[] fields = bean.getClass().getDeclaredFields();
				for (Field field : fields) {

					fixRow = Math.max(importSheetHeader(field, bean.getType()), fixRow);
				}
			}

//			if(fieldInfoList.size() > 0) {
//				annoList = true;
//			}
		} else {
			columnSize = headers.size();
		}
		
		if (fixRow == 0 || fixRow < startRow) {
			fixRow = startRow;
		}

		SXSSFSheet xsheet = workbook.createSheet(sheetName);
		xsheet.flushRows(list.size() + startRow);

		SXSSFRow curRow = null;
		Cell cell = null;

		// header
		curRow = xsheet.createRow(0);
		if(!doubleHeaders.isEmpty()) {

			ListOrderedMap<String, HeaderCell> doubleHeader = null;
			for(int rowIdx1 = 0; rowIdx1 < doubleHeaders.size(); rowIdx1++) {
				if (rowIdx1 > 0) {
					if (xsheet.getRow(rowIdx1) == null) {
						curRow = xsheet.createRow(rowIdx1);
    				} else {
    					curRow = xsheet.getRow(rowIdx1);
    				}
				}

				doubleHeader = doubleHeaders.get(rowIdx1);
				if(columnSize == 0) columnSize = doubleHeader.size();
				for(HashMap.Entry<String, HeaderCell> entry : doubleHeader.entrySet()) {
					HeaderCell header = entry.getValue();
					if (curRow.getCell(header.getIdx()) == null) {
						cell = curRow.createCell(header.getIdx());
					} else {
						cell = curRow.createCell(header.getIdx());
					}
					cell.setCellValue(header.getName());
					if (header.isRed()) {
						cell.setCellStyle(workbook.getCellStyleAt(5));
					} else if(header.isComment()) {
						cell.setCellStyle(workbook.getCellStyleAt(6));
					} else {
						cell.setCellStyle(workbook.getCellStyleAt(1));
					}

					if (header.getRowspan() > 1 || header.getColspan() > 1) {
						int rowidx = header.getRowIdx() -1;
						int rowspan = header.getRowspan() -1;
						int colidx = header.getIdx();
						int colspan = header.getColspan() -1;
						CellRangeAddress cellRangeAddress = new CellRangeAddress(
								rowidx,
								rowidx + rowspan,
								colidx,
								colidx + colspan);

						logger.debug("MERGE CELL [" + header.getName() + "] : row "
								+ rowidx +" - "+ (rowidx + rowspan)
								+" / col "+ colidx +" - "+ (colidx + colspan));
						SXSSFRow tempRow = null;
						Cell tempCell = null;
						for (int temprow = rowidx+1; temprow <= (rowidx + rowspan) ; temprow++) {
	        				if (xsheet.getRow(temprow) == null) {
	        					tempRow = xsheet.createRow(temprow);
	        					tempCell = tempRow.createCell(colidx);
        						tempCell.setCellStyle(workbook.getCellStyleAt(1));
	        				} else {
	        					tempRow = xsheet.getRow(temprow);
	        					if (tempRow.getCell(colidx) == null) {
	        						tempCell = tempRow.createCell(colidx);
	        						tempCell.setCellStyle(workbook.getCellStyleAt(1));
	        					}
	        				}
        				}
        				for (int tempcell = colidx+1; tempcell <= (colidx + colspan) ; tempcell++) {
        					if (curRow.getCell(tempcell) == null) {
        						tempCell = curRow.createCell(tempcell);
        						tempCell.setCellStyle(workbook.getCellStyleAt(1));
        					}
        				}
						xsheet.addMergedRegion(cellRangeAddress);
					}

					String codeName = header.getCodeName();
					if (codes != null && codes.containsKey(codeName) && codes.get(codeName) != null) {
						header.setCodeMap(getHeaderCellCodeMap(codes.get(codeName)));
					}

					if (header.isHeaderCode()) {
						try {
							setHeaderDropDown(header, xsheet, rowIdx1, header.getIdx());
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						try {
							setCellDropDown(header, xsheet, doubleHeaders.size(), this.DROPDOWN_DEFAULT_ROW, header.getIdx());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					
				}
			}
		} else if(!headers.isEmpty()) {

			if(columnSize == 0) columnSize = headers.size();

			for(HashMap.Entry<String, HeaderCell> entry : headers.entrySet()) {
				HeaderCell header = entry.getValue();
				cell = curRow.createCell(header.getIdx());
				cell.setCellValue(header.getName());
				if (header.isRed()) {
					cell.setCellStyle(workbook.getCellStyleAt(5));
				} else {
					cell.setCellStyle(workbook.getCellStyleAt(1));
				}
				
				String codeName = header.getCodeName();
				if (codes != null && codes.containsKey(codeName) && codes.get(codeName) != null) {
					header.setCodeMap(getHeaderCellCodeMap(codes.get(codeName)));
				}

				try {

					setCellDropDown(header, xsheet, header.getIdx());

				} catch (Exception e) {
					e.printStackTrace();
				}

				/*
				 * FORMULA 및 CELL TYPE 지정
				 */
				if (!ObjectUtil.isEmpty(header.getFormula())) {
					
					int rowCount = this.DROPDOWN_DEFAULT_ROW;
					SXSSFRow tempTypeRow = null;
					SXSSFCell tempTypeCell = null;
					CellStyle celFormatStyle = workbook.createCellStyle();
					for (int rowIdxTemp = startRow; rowIdxTemp < (startRow + rowCount); rowIdxTemp++) {
						if (rowIdxTemp > 0) {
							if (xsheet.getRow(rowIdxTemp) == null) {
								tempTypeRow = xsheet.createRow(rowIdxTemp);
		    				} else {
		    					tempTypeRow = xsheet.getRow(rowIdxTemp);
		    				}
						}
						tempTypeCell = tempTypeRow.createCell(header.getIdx());
						if ("text".equals(header.getFormula())) {
							celFormatStyle.setDataFormat((short)BuiltinFormats.getBuiltinFormat("text")); 
							tempTypeCell.setCellStyle(celFormatStyle);
						} else if ("date".equals(header.getFormula())) {
							XSSFDataFormat format = (XSSFDataFormat) workbook.createDataFormat();
							celFormatStyle.setDataFormat(format.getFormat("yyyy/MM/dd"));
							tempTypeCell.setCellStyle(celFormatStyle);
						} else {
							tempTypeCell.setCellType(CellType.FORMULA);
							tempTypeCell.setCellFormula(header.getFormula());
						}
					}
				}
			}

		} else {

			if(columnSize == 0) columnSize = fieldInfoList.size();

			for (ImportFieldInfo fieldInfo : fieldInfoList) {
				cell = curRow.createCell(fieldInfo.col);
				cell.setCellValue(fieldInfo.name);
				cell.setCellStyle(workbook.getCellStyleAt(1));

				if(!ObjectUtil.isEmpty(fieldInfo.codeObj)) {
					// 데이터 유효성 설정
					CellRangeAddressList addressList = new CellRangeAddressList(1, 399, fieldInfo.col, fieldInfo.col);
//					XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(xsheet);
					DataValidationHelper validationHelper = xsheet.getDataValidationHelper();

					String[] dataList = null;
					if(!ObjectUtil.isEmpty(fieldInfo.codeObj)) {
						if (fieldInfo.codeObj instanceof String[]) {
							dataList = (String[]) fieldInfo.codeObj;
						} else {
							fieldInfo.setCodeMap(getHeaderCellCodeMap(fieldInfo.codeObj));
						}
					}

					DataValidationConstraint constraint = validationHelper.createExplicitListConstraint(dataList);
					DataValidation dataValidation = validationHelper.createValidation(constraint, addressList);
					dataValidation.setSuppressDropDownArrow(true);
					dataValidation.setShowErrorBox(true);
					dataValidation.createErrorBox("ERROR", "올바른 데이터를 선택하세요");
					xsheet.addValidationData(dataValidation);
				}
			}
		}


		// body
		int rowIdx = startRow;

		for(T obj : list) {
			if (obj instanceof ExcelMap) {
				curRow = xsheet.createRow(rowIdx);

				int cellIdx = 0;
				for (Object key : ((ExcelMap)obj).keySet()) {
					cell = creatCell(curRow, cellIdx, ((ExcelMap)obj).get(key));
					HeaderCell headerCell =  headers.get(key);

					if(headerCell != null && headerCell.getAlign().equals("left")) {
						cell.setCellStyle(workbook.getCellStyleAt(3));
					} else if(headerCell != null && headerCell.getAlign().equals("right")) {
						cell.setCellStyle(workbook.getCellStyleAt(4));
					} else {
						cell.setCellStyle(workbook.getCellStyleAt(2));
					}
					cellIdx++;
				}
			} else {
				curRow = xsheet.createRow(rowIdx);
				@SuppressWarnings("unchecked")
				Field[] objFields = ((Class<T>)obj.getClass()).getDeclaredFields();

				Class superClazz = obj.getClass().getSuperclass();
				while(superClazz != null) {
					Field[] superObjFields = superClazz.getDeclaredFields();
			    	if (superObjFields != null) {
			    		objFields = ArrayUtils.addAll(objFields, superObjFields);
			    	}

			    	superClazz = superClazz.getSuperclass();
				}

			    int col = 0;
	//		    logger.debug("rowIdx : " + rowIdx);
				for (Field field : objFields) {
					if(!ObjectUtil.isEmpty(headers)) {
						HeaderCell headerCell =  headers.get(field.getName());
						if(null != headerCell) {
							field.setAccessible(true);
							String fieldName = field.getName();
							if(Pattern.matches("^[a-z]{1}[A-Z]+.*$", fieldName)) {
								fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
							}
							Object fieldValue = PropertyUtils.getProperty(obj, fieldName);

							Map<String,String> codeMap = headerCell.getCodeMap();
							if (!ObjectUtil.isEmpty(codeMap)) {
								if(codeMap.containsKey(fieldValue)) {
									fieldValue = codeMap.get(fieldValue);
								}
							}


							cell = creatCell(curRow, headerCell.getIdx(), fieldValue);


							// workbook.getCellStyleAt(2) : center
							// workbook.getCellStyleAt(3) : left
							// workbook.getCellStyleAt(4) : right
							if(headerCell.getAlign().equals("left")) {
								cell.setCellStyle(workbook.getCellStyleAt(3));
							} else if(headerCell.getAlign().equals("right")) {
								cell.setCellStyle(workbook.getCellStyleAt(4));
							} else {
								cell.setCellStyle(workbook.getCellStyleAt(2));
							}
						}

					} else {
						ImportField ifAn = field.getAnnotation(ImportField.class);
						if (ifAn != null) {
							int childCol = ExcelUtils.toColIndex(ifAn.col()) - 1;

							ImportFieldInfo ifi = importSheetHeader(ifAn, childCol);

							field.setAccessible(true);

							String fieldName = field.getName();
							if(Pattern.matches("^[a-z]{1}[A-Z]+.*$", fieldName)) {
								fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
							}
							Object fieldValue = PropertyUtils.getProperty(obj, fieldName);

							Map<String,String> codeMap = ifi.getCodeMap();
							if (!ObjectUtil.isEmpty(codeMap)) {
								if(codeMap.containsKey(fieldValue)) {
									fieldValue = codeMap.get(fieldValue);
								}
							}

							cell = creatCell(curRow, childCol, fieldValue);

							// workbook.getCellStyleAt(2) : center
							// workbook.getCellStyleAt(3) : left
							// workbook.getCellStyleAt(4) : right

							if(ifAn.align().equals("left")) {
								cell.setCellStyle(workbook.getCellStyleAt(3));
							} else if(ifAn.align().equals("right")) {
								cell.setCellStyle(workbook.getCellStyleAt(4));
							} else {
								cell.setCellStyle(workbook.getCellStyleAt(2));
							}
						}
					}
				}
			}

			rowIdx++;
		}
		
		/*
		 * 코멘트 추가 
		 */
		if(!ObjectUtil.isEmpty(comments)) {

			int commentStartRow = COMMENT_START_ROW + startRow;
			int commentRow = 0;
			for(String comment : comments) {
				
				curRow = xsheet.createRow(commentStartRow + commentRow);
				
				cell = curRow.createCell(0);
				cell.setCellValue(comment);
				cell.setCellStyle(workbook.getCellStyleAt(6));
				commentRow++;
			}

		}

		if(fixRow > 0) {
			// 틀고정 1row
			xsheet.createFreezePane(0, fixRow, 0, fixRow );
		}

		xsheet.trackAllColumnsForAutoSizing();

	}

	private Map getHeaderCellCodeMap (Object codeObj) {

		if(codeObj instanceof Map) {
			return (Map)codeObj;
		} else if(codeObj instanceof List) {
			List<Object> tlist = (List)codeObj;
			Map<String, String> tMap = new HashMap<String, String>();
			if(tlist.size() > 0 && tlist.get(0) instanceof CodeDto) {
				for(Object obj : tlist) {
					CodeDto codeDto = (CodeDto)obj;
					tMap.put(codeDto.getCodeId(), codeDto.getCodeName());
				}
			}

			return tMap;
		}

		return null;
	}

	private Cell creatCell(Row row, int cellIdx, Object obj) {
		Cell cell = row.createCell(cellIdx);

		if(obj instanceof Date)
			cell.setCellValue((Date)obj);
        else if(obj instanceof Boolean)
        	cell.setCellValue((Boolean)obj);
        else if(obj instanceof Integer)
        	cell.setCellValue((Integer)obj);
        else if(obj instanceof Long)
        	cell.setCellValue((Long)obj);
        else if(obj instanceof Double)
        	cell.setCellValue((Double)obj);
        else if(obj instanceof BigDecimal)
        	cell.setCellValue(((BigDecimal)obj).doubleValue());
        else if(obj instanceof BigInteger)
        	cell.setCellValue(((BigInteger)obj).doubleValue());
        else
        	cell.setCellValue((String)obj);

		return cell;
	}

	private void setCellDropDown(HeaderCell header, SXSSFSheet xsheet, int col) throws Exception {
		setCellDropDown(header, xsheet, this.DROPDOWN_START_ROW, this.DROPDOWN_DEFAULT_ROW, col);
	}

	private void setHeaderDropDown(HeaderCell header, SXSSFSheet xsheet, int firstRow, int col) throws Exception {
		String codeName = header.getCodeName();
		
		if (StringUtils.isNotEmpty(codeName)) {
			Object dataObj = codes.get(codeName);
			
			String[] dataList = null;
			if(dataObj instanceof String[]) {
				dataList = (String[]) dataObj;
			}
			
			if (!ObjectUtil.isEmpty(dataList)) {
				if(dataList.length < 20) {
					
//					XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(xsheet);
					DataValidationHelper dvHelper = xsheet.getDataValidationHelper();
					XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper.createExplicitListConstraint(dataList);
					CellRangeAddressList addressList = new CellRangeAddressList(firstRow, firstRow, col, col);
					XSSFDataValidation validation = (XSSFDataValidation)dvHelper.createValidation(dvConstraint, addressList);
					validation.setShowErrorBox(true);
					validation. createErrorBox("ERROR MEESAGE:Invalid Data", "Please provide valid data in the dropdown list.");
					
					xsheet.addValidationData(validation);
				} else {
					
					/*
					 *
					 */
					int sheetIndex = workbook.getSheetIndex(xsheet.getSheetName());
					String codeSheetName = "CodeSheet_"+sheetIndex;
					
					SXSSFSheet hidden = workbook.getSheet(codeSheetName);
					if (hidden == null) {
						hidden = workbook.createSheet(codeSheetName);
					}
					
					for (int i = 0, length = dataList.length; i < length; i++) {
						String name = dataList[i];
						SXSSFRow row = hidden.getRow(i);
						if (row == null)
							row = hidden.createRow(i);
						
						SXSSFCell cell = row.createCell(col);
						cell.setCellValue(name);
					}
					Name namedCell = workbook.createName();
					namedCell.setNameName(codeName+"_"+col);
					
					String colString = CellReference.convertNumToColString(col);
					namedCell.setRefersToFormula(codeSheetName+"!$"+colString+"$1:$"+colString+"$" + dataList.length);
					
					DataValidationHelper helper = null;
					DataValidationConstraint constraint = null;
					DataValidation validation = null;
					
					helper = xsheet.getDataValidationHelper();
					constraint = helper.createFormulaListConstraint(codeName+"_"+col);
					validation = helper.createValidation(constraint, new CellRangeAddressList(firstRow, firstRow, col, col));
					xsheet.addValidationData(validation);
					
					workbook.setSheetHidden(workbook.getSheetIndex(codeSheetName), true); //true 숨김, false 보임
					
				}
			}
		}
	}
	private void setCellDropDown(HeaderCell header, SXSSFSheet xsheet, int firstRow, int rowCount, int col) throws Exception {
		String codeName = header.getCodeName();
		if (firstRow < 1) rowCount = this.DROPDOWN_START_ROW;
		if (rowCount < 1) rowCount = this.DROPDOWN_DEFAULT_ROW;

		if (StringUtils.isNotEmpty(codeName)) {
			Object dataObj = codes.get(codeName);

			String[] dataList = null;
			if(dataObj instanceof String[]) {
				dataList = (String[]) dataObj;
			}

			if (!ObjectUtil.isEmpty(dataList)) {
				if(dataList.length < 20) {

//					XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(xsheet);
					DataValidationHelper dvHelper = xsheet.getDataValidationHelper();
					XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper.createExplicitListConstraint(dataList);
					CellRangeAddressList addressList = new CellRangeAddressList(firstRow, firstRow + rowCount -1, col, col);
					XSSFDataValidation validation = (XSSFDataValidation)dvHelper.createValidation(dvConstraint, addressList);
					validation.setShowErrorBox(true);
					validation. createErrorBox("ERROR MEESAGE:Invalid Data", "Please provide valid data in the dropdown list.");

					xsheet.addValidationData(validation);
				} else {

					/*
					 *
					 */
					int sheetIndex = workbook.getSheetIndex(xsheet.getSheetName());
					String codeSheetName = "CodeSheet_"+sheetIndex;

					SXSSFSheet hidden = workbook.getSheet(codeSheetName);
					if (hidden == null) {
						hidden = workbook.createSheet(codeSheetName);
					}

					for (int i = 0, length = dataList.length; i < length; i++) {
						String name = dataList[i];
						SXSSFRow row = hidden.getRow(i);
						if (row == null)
							row = hidden.createRow(i);

						SXSSFCell cell = row.createCell(col);
						cell.setCellValue(name);
					}
					Name namedCell = workbook.createName();
					namedCell.setNameName(codeName+"_"+col);

					String colString = CellReference.convertNumToColString(col);
					namedCell.setRefersToFormula(codeSheetName+"!$"+colString+"$1:$"+colString+"$" + dataList.length);

					DataValidationHelper helper = null;
					DataValidationConstraint constraint = null;
					DataValidation validation = null;

					helper = xsheet.getDataValidationHelper();
					constraint = helper.createFormulaListConstraint(codeName+"_"+col);
					validation = helper.createValidation(constraint, new CellRangeAddressList(firstRow, firstRow + rowCount -1, col, col));
					xsheet.addValidationData(validation);

					workbook.setSheetHidden(workbook.getSheetIndex(codeSheetName), true); //true 숨김, false 보임

				}
			}
		}
	}
	private ImportFieldInfo importSheetHeader(ImportField ifAn, int childCol) throws Exception {

		if (ifAn == null) return null;

		Object codeObj = null;
		if(!ObjectUtil.isEmpty(ifAn.code()) && !ObjectUtil.isEmpty(codes) && !ObjectUtil.isEmpty(codes.get(ifAn.code()))) {
			codeObj = codes.get(ifAn.code());
			if (codeObj instanceof String[]) {
			} else {
				codeObj = getHeaderCellCodeMap(codeObj);
			}
		}

		ImportFieldInfo ifi = new ImportFieldInfo(childCol
				, ifAn.name()
				, ifAn.autosize()
				, ifAn.align()
				, codeObj
				);


		fieldInfoList.add(ifi);

		return ifi;
	}

	private <T> int importSheetHeader(Field field, Class<T> clazz) throws Exception {
		ImportListField ilfAn = field.getAnnotation(ImportListField.class);
		if (ilfAn != null) {
			Field[] childFields = clazz.getDeclaredFields();
			for (Field childField : childFields) {
				ImportField ifAn = childField.getAnnotation(ImportField.class);

				if (ifAn != null) {
					int childCol = ExcelUtils.toColIndex(ifAn.col()) - 1;

					Object codeObj = null;
					if(!ObjectUtil.isEmpty(ifAn.code()) && !ObjectUtil.isEmpty(codes) && !ObjectUtil.isEmpty(codes.get(ifAn.code()))) {
						if (codeObj instanceof String[]) {
						} else {
							codeObj = getHeaderCellCodeMap(codeObj);
						}
					}

					fieldInfoList.add(new ImportFieldInfo(null
														, childField
														, ilfAn.startRow()
														, ilfAn.endRow()
														, childCol
														, ifAn.name()
														, ifAn.autosize()
														, ifAn.align()
														, codeObj
														));
				}
			}
			return ilfAn.startRow();
		}
		return 0;
	}

}
