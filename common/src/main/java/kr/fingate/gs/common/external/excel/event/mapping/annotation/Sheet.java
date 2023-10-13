package kr.fingate.gs.common.external.excel.event.mapping.annotation;

import java.lang.annotation.*;

// 처리대상 시트를 지정하기 위한 어노테이션
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Sheet {

	// 시트 이름의 기본값
	String DFAULT_NAME = "";

	// 시트 이름
	String name() default DFAULT_NAME;

	// 시트 인덱스의 기본값
	int DEFAULT_INDEX = -1;

	// 시트 인덱스
	int index() default DEFAULT_INDEX;

}
