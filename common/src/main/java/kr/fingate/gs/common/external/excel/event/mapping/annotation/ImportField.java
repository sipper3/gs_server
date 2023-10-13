package kr.fingate.gs.common.external.excel.event.mapping.annotation;

import java.lang.annotation.*;

// 단일 값의 매핑을 위한 어노테이션
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ImportField {
	// 행의 기본값
	int DFAULT_ROW = 0;
	String DEFAULT_VAL = "";
	String DEFAULT_AUTO_SIZE = "Y";
	String DEFAULT_ALIGN = "center";

	// 행
	int row() default DFAULT_ROW;

	// 열
	String col();

	// 명
	String name() default DEFAULT_VAL;

	// 컬럼 사이즈 자동 조정
	String autosize() default DEFAULT_AUTO_SIZE;

	// 죄우 정렬(center, right, left)
	String align() default DEFAULT_ALIGN;

	// 코드
	String code() default DEFAULT_VAL;
}
