package kr.fingate.gs.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 테이블 변경시 변경 대상 table/field 를 설정 annotation
 * @author swkim
 *
 */

@Target({ElementType.TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ComparableEntity {
	public String value() default "";
}
