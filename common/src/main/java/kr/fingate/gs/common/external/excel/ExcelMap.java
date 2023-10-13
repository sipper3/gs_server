package kr.fingate.gs.common.external.excel;

import kr.fingate.gs.common.util.ConvertUtil;
import org.apache.commons.collections4.map.ListOrderedMap;

public class ExcelMap extends ListOrderedMap<Object, Object> {

    /**
     * key 에 대하여 Camel Case 변환하여 super.put
     * (ListOrderedMap) 을 호출한다.
     * @param key
     *        - '_' 가 포함된 변수명
     * @param value
     *        - 명시된 key 에 대한 값 (변경 없음)
     * @return previous value associated with specified
     *         key, or null if there was no mapping for
     *         key
     */
    @Override
    public Object put(Object key, Object value) {
    	return super.put(ConvertUtil.convertToCamelCase((String) key), value);
    }

}
