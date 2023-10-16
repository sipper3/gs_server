package kr.fingate.gs.common.external.excel.event.mapping;


import kr.fingate.gs.common.external.excel.event.mapping.annotation.ExcelDateForm;
import kr.fingate.gs.common.external.excel.event.mapping.annotation.ImportField;
import kr.fingate.gs.common.external.excel.event.mapping.annotation.ImportListField;
import kr.fingate.gs.common.external.excel.event.util.ExcelUtils;
import kr.fingate.gs.common.external.excel.event.util.ReflectionUtils;
import org.apache.poi.ss.usermodel.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNumeric;

/**
 * row, col, value를 받고 bean에 매핑하는 클래스
 */
public class FieldValueArranger {

	protected static final Logger logger = LoggerFactory.getLogger(FieldValueArranger.class);

	// 값을 설정하는 대상 bean
	private Object bean = null;
	// 설정 대상 필드의 정보
	private List<ImportFieldInfo> fieldInfoList = new ArrayList<ImportFieldInfo>();

	public FieldValueArranger(Object bean) throws Exception {
		this.bean = bean;
		init(bean);
	}

	// 매핑되는 필드 정보를 수집합니다.
	private void init(Object bean) throws Exception {
		Field[] fields = bean.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (!initImportField(field)) {
				initImportBeanList(field);
			}
		}
	}

	// 매핑 대상의 단일 필드 정보를 수집합니다.
	private boolean initImportField(Field field) throws Exception {
		ImportField ifAn = field.getAnnotation(ImportField.class);
		if (ifAn != null) {
			fieldInfoList.add(new ImportFieldInfo(field, ifAn.row(), ExcelUtils.toColIndex(ifAn.col())));
			return true;
		}
		return false;
	}

	// 매핑 대상 목록 필드 정보를 수집합니다.
	private void initImportBeanList(Field field) throws Exception {
		ImportListField ilfAn = field.getAnnotation(ImportListField.class);
		if (ilfAn != null) {
			Class<?> childClazz = ReflectionUtils.getFirstChildClazz(field);
			Field[] childFields = childClazz.getDeclaredFields();
			for (Field childField : childFields) {
				ImportField ifAn = childField.getAnnotation(ImportField.class);
				ExcelDateForm exclForm = childField.getAnnotation(ExcelDateForm.class);
				if (ifAn != null) {
					int childCol = ExcelUtils.toColIndex(ifAn.col());
					if (exclForm != null){
						String dateForm = exclForm.dateForm();
						// 하위 필드를 추가
						fieldInfoList.add(new ImportFieldInfo(field, childField, ilfAn.startRow(), ilfAn.endRow(), childCol, dateForm));
					}else{
						// 하위 필드를 추가
						fieldInfoList.add(new ImportFieldInfo(field, childField, ilfAn.startRow(), ilfAn.endRow(), childCol));
					}
				}
			}
		}
	}

	// row, col을 바탕으로 필드를 식별 값을 설정합니다
	public boolean arrange(int row, int col, String value) throws Exception {

		ImportFieldInfo info = getImportField(row, col);

		if (info != null) {
			if(info.dateForm != "" && info.dateForm != null){
				value = valiDateValue(info.dateForm, value);
			}
			if (info.isChildField()) {
				// 목록 형식의 항목에 설정
				info.parentField.setAccessible(true);
				@SuppressWarnings("unchecked")
				List<Object> children = (List<Object>) info.parentField.get(bean);
				info.parentField.setAccessible(false);

				int rowIndex = info.getListIndex(row);

				Object childBean = null;
				if (children.size() - 1 < rowIndex) {
					// 자식 클래스를 얻을
					Class<?> childClazz = info.childClazz;
					// 자식 클래스의 인스턴스를 만들
					childBean = childClazz.newInstance();
					while (children.size() < rowIndex) {
						children.add(null);
					}
					children.add(childBean);
				} else {
					childBean = children.get(rowIndex);
				}
				// 자식 클래스의 필드로 설정
				return setFieldValue(childBean, info.field, value);
			} else {
				// 단일 항목으로 설정
				return setFieldValue(bean, info.field, value);
			}
		}
		return false;
	}

	// row, col에 해당하는 필드 정보를 얻음
	private ImportFieldInfo getImportField(int row, int col) throws Exception {
		return getImportField(null, row, col);
	}

	// row, col에 해당하는 필드 정보를 얻음
	private ImportFieldInfo getImportField(Field pareintField, int row, int col) throws Exception {
		for (ImportFieldInfo range : fieldInfoList) {
			if (range.isIncluded(pareintField, row, col)) {
				return range;
			}
		}
		return null;
	}

	// field의 ​​class에 맞는 형식으로 변환 값을 설정
	private boolean setFieldValue(Object bean, Field field, String value) throws Exception {
		field.setAccessible(true);
		if (field.getType().equals(String.class)) {
			// 문자열의 경우
			field.set(bean, value);
		} else if (field.getType().equals(Integer.class)) {
			// 수치의 경우
			field.set(bean, ExcelUtils.getIntegerValue(value));
		} else if (field.getType().equals(Long.class)) {
			// 수치의 경우
			field.set(bean, ExcelUtils.getLongValue(value));
		} else if (field.getType().equals(Short.class)) {
			// 수치의 경우
			field.set(bean, ExcelUtils.getShortValue(value));
		} else if (field.getType().equals(Double.class)) {
			// 수치의 경우
			field.set(bean, ExcelUtils.getDoubleValue(value));
		} else if (field.getType().equals(Date.class)) {
			// 날짜의 경우
			field.set(bean, ExcelUtils.getDateValue(value));
		} else if (field.getType().equals(Boolean.class)) {
			// 부울의 경우
			field.set(bean, ExcelUtils.getBooleanValue(value));
		} else {
			// 기타
			field.setAccessible(false);
			return false;
		}
		field.setAccessible(false);
		return true;
	}

	//field값이 날짜인 경우 날짜에 맞는 포멧을 체크하는 로직
	private String valiDateValue(String dateForm, String value)  throws Exception {
		SimpleDateFormat chkForm = new SimpleDateFormat(dateForm);
		String valChk = value;
		//엑셀로 인해서 날짜가 1900부터 현재 일수로 변경된 경우(5자리)
		if(valChk.length() == 5 && isNumeric(valChk)){
			Date tmpDate = DateUtil.getJavaDate(Double.parseDouble(valChk));
			String chkDate = chkForm.format(tmpDate);
			valChk = chkDate;
			return valChk;
		}else{
			valChk = value.replaceAll("[^0-9]","");
			if(valChk.length() == dateForm.length()) {
				if (chckDate(dateForm, valChk)) {
					return valChk;
				}
			}else {
				throw new Exception();
			}
		}
		return valChk;
	}
	//날짜 벨리데이션 체크
	public static boolean chckDate(String dateForm, String value) throws Exception {
		boolean res = true;
        SimpleDateFormat dateFormatParser = new SimpleDateFormat(dateForm);
        dateFormatParser.setLenient(false);
        dateFormatParser.parse(value);
        return res;
    }

}
