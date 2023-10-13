package kr.fingate.gs.common.external.excel.event.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ReflectionUtils {

	private ReflectionUtils() {
	}

	// List 자식 요소의 클래스를 취득하는 메소드
	public static Class<?> getFirstChildClazz(Field field) {
		// Generic을 포함한 Type을 가져옴
		Type type = field.getGenericType();
		// Generic의 Type을 가져옴
		ParameterizedType pType = (ParameterizedType) type;
		return (Class<?>) pType.getActualTypeArguments()[0];
	}
}
