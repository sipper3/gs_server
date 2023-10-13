package kr.fingate.gs.common.external.excel.event.mapping.annotation;

import java.lang.annotation.*;

// 엑셀의 날짜 벨리데이션을 위한 어노테이션

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelDateForm {

	String DEFAULT_DATEFORM ="yyyyMMdd";
	String dateForm() default DEFAULT_DATEFORM;
}
