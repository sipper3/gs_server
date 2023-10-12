package kr.fingate.gs.common.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {NotDefaultValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotDefault {

    String message() default "필수 입력입니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
