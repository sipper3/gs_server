package kr.fingate.gs.auth.util;

import com.google.common.base.CaseFormat;
import kr.fingate.gs.common.annotation.ComparableEntity;
import kr.fingate.gs.common.dto.hstry.HstryDto;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("unchecked")
public class HistoryUtil<T, U extends HstryDto> {
	static final Logger logger = LoggerFactory.getLogger(HistoryUtil.class);

	/**
	 *
	 * @param
	 * @param changeDto : 변경 후
	 * @param orgnlDto : 변경 전
	 * @param history
	 * @return
	 * @throws
	 */
	public List<U> getHistoryList(T changeDto, T orgnlDto, U history) throws
	IllegalArgumentException, IllegalAccessException, CloneNotSupportedException
	{
		if(null == changeDto) {
			throw new IllegalArgumentException("changeDto");
		} else if(history.getRegUserNo() == 0) {
			
			throw new IllegalArgumentException("regUserNo");
		}

		history.setOprtnType("U");
		if(orgnlDto == null) {
			history.setOprtnType("C");
		}

		ComparableEntity ifAn = changeDto.getClass().getAnnotation(ComparableEntity.class);
		String enttyName = "";
		if(ifAn != null) {
			enttyName = ifAn.value();
		}
		history.setEnttyName(enttyName);	// 테이블명

		return this.getChanges(changeDto, orgnlDto, history);
	}

	private List<U> getChanges(T dest, T orig, U history) throws
		IllegalArgumentException, IllegalAccessException, CloneNotSupportedException {
		List<U> list = new ArrayList<U>();
		Field[] fields = dest.getClass().getDeclaredFields();
		
		Class superClazz = dest.getClass().getSuperclass();
	    if(superClazz != null){
	    	Field[] superObjFields = superClazz.getDeclaredFields();
	    	if (superObjFields != null)
	    		fields = ArrayUtils.addAll(fields, superObjFields);
	    }
	    
		if (fields != null) {

			String[] exFields = {"regUserNo","regData", "modUserNo", "modDate"};
			for (Field field : fields) {

				String atrbtName = field.getName();
				if(Arrays.stream(exFields).anyMatch(atrbtName::equals)) {
					continue;
				}

				atrbtName = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, atrbtName);

				field.setAccessible(true);

				Object filedDestObj = field.get(dest);
				Object filedOrigObj = orig == null ? null : field.get(orig);

				String destStr = Objects.toString(filedDestObj, StringUtils.EMPTY);
				String origStr = Objects.toString(filedOrigObj, StringUtils.EMPTY);

				if (origStr.equals(destStr)) {
					continue;
				}

				U hist = (U)history.clone();

				hist.setAtrbtName(atrbtName);	// 컬럼명

				if(filedDestObj instanceof Double) {

					NumberFormat f = NumberFormat.getInstance();
					f.setGroupingUsed(false); // 3자리마다 콤마 표시 제거(false)
					double d_dest = Double.parseDouble(destStr);
					double d_orig = Double.parseDouble(origStr);

					destStr = f.format(d_dest);	// double 타입일때 숫자가 지수로 들어가는것 방지하기 위함
					origStr = f.format(d_orig);
				}

				hist.setOrgnlData(origStr);
				hist.setChangeData(destStr);

				list.add(hist);

				field.setAccessible(false);
			}
		}

		return list;
	}
}
