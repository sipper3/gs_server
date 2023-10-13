package kr.fingate.gs.common.external.excel.event.mapping.annotation;

import java.lang.annotation.*;

// 반복 항목(시작행~종료행)을 설정하는 어노테이션
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ImportListField {
	// 종료 행의 기본값
	int DFAULT_END_ROW = Integer.MAX_VALUE;

	// 시작 행
	int startRow();

	// 종료 행
	int endRow() default DFAULT_END_ROW;
}
