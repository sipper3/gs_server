package kr.fingate.gs.common.external.excel;

import kr.fingate.gs.common.external.excel.event.mapping.annotation.ImportListField;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExcelExportModel<T> {

	private Class<T> type;

	private String sheetName;

	private int fixRow;

	@ImportListField(startRow = 1)
	private List<?> list = new ArrayList<T>();

	private ExcelHeader header = new ExcelHeader();

	public ExcelExportModel() {}
	public ExcelExportModel(Class<T> clazz) {
        this.type = clazz;
    }


}
