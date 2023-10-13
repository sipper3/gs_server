package kr.fingate.gs.common.external.excel.event.mapping;

import kr.fingate.gs.common.external.excel.event.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 값 설정 대상 필드의 정보를 관리하는 클래스
 */
public class ImportFieldInfo {

	// List의 자식 클래스
	Class<?> childClazz = null;
	// 부모의 필드
	Field parentField = null;
	// 대상 필드
	Field field = null;
	// 값을 얻을 대상이 되는 범위
	int startRow = 0;
	int endRow = 0;
	int col = 0;
	String name = "";
	String autoSize = "Y";
	String align = "Center";
	String dateForm = "";
	Object codeObj = null;
	Map codeMap = null;

	// bean의 field의 생성자
	ImportFieldInfo(Field field, int row, int col) {
		this(null, field, row, row, col);
	}

	// List에 보관 된 bean의 field의 생성자
	ImportFieldInfo(Field parentField, Field field, int startRow, int endRow, int col) {
		this.field = field;
		this.startRow = startRow;
		this.endRow = endRow;
		this.col = col;
		this.parentField = parentField;
		if (parentField != null) {
			this.childClazz = ReflectionUtils.getFirstChildClazz(parentField);
		}
	}

	ImportFieldInfo(Field parentField, Field field, int startRow, int endRow, int col, String dateForm) {
		this.field = field;
		this.startRow = startRow;
		this.endRow = endRow;
		this.col = col;
		this.parentField = parentField;
		this.dateForm = dateForm;
		if (parentField != null) {
			this.childClazz = ReflectionUtils.getFirstChildClazz(parentField);
		}
	}
	ImportFieldInfo(int col, String name, String autoSize, String align, Object codeObj) {
//		this.field = field;
//		this.startRow = startRow;
//		this.endRow = endRow;
		this.col = col;
		this.name = name;
		this.autoSize = autoSize;
		this.align = align;
//		this.parentField = parentField;
//		if (parentField != null) {
//			this.childClazz = ReflectionUtils.getFirstChildClazz(parentField);
//		}

		if (codeObj instanceof Map) {
			this.codeMap = (Map) codeObj;
		} else {
			this.codeObj = codeObj;
		}
	}

	// List에 보관 된 bean의 field의 생성자
		ImportFieldInfo(Field parentField, Field field, int startRow, int endRow, int col, String name, String autoSize, String align, Object codeObj) {
			this.field = field;
			this.startRow = startRow;
			this.endRow = endRow;
			this.col = col;
			this.name = name;
			this.autoSize = autoSize;
			this.align = align;
			if (parentField != null) {
				this.childClazz = ReflectionUtils.getFirstChildClazz(parentField);
			}

			if (codeObj instanceof Map) {
				this.codeMap = (Map) codeObj;
			} else {
				this.codeObj = codeObj;
			}
		}

	// 지정된 row, col의 field가 값을 검색 할 대상인지 판단
	boolean isIncluded(int row, int col) {
		return isIncluded(null, row, col);
	}

	// 지정된 row, col의 field가 값을 검색 할 대상인지 판단 (List에 보관 된 bean의 field 용)
	boolean isIncluded(Field parentField, int row, int col) {
		if (parentField == null || parentField == this.parentField) {
			if (startRow <= row && row <= endRow && this.col == col) {
				return true;
			}
		}
		return false;
	}

	// row에 대응하는 List의 index를 취득
	int getListIndex(int row) {
		return row - startRow;
	}

	// List 하위의 bean의 field인지 판단
	boolean isChildField() {
		return parentField != null;
	}

	@Override
	public String toString() {
		return "ImportFieldInfo [childClazz =" + childClazz + ", parentField ="
				+ parentField + ", field =" + field + ", startRow =" + startRow
				+ ", endRow =" + endRow + ", col =" + col + "]";
	}

	public Map getCodeMap() {
		return codeMap;
	}

	public void setCodeMap(Map codeMap) {
		this.codeMap = codeMap;
	}


}
