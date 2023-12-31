package kr.fingate.gs.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PrivateSql {

    boolean masking() default true;

    boolean encrypt() default true;

    boolean decrypt() default true;
}
