package kr.fingate.gs.common.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static kr.fingate.gs.common.validate.ConditionalMode.AND;

@Repeatable(Conditional.List.class)
@Constraint(validatedBy = {ConditionalValidator.class})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Conditional {

    String message() default "";
    Class<?>[] groups() default {};

    //조건부 대상이 되는 필드명
    String selected() default "";

    //조건부 대상이 요구되는 값
    String[] values() default {};

    //값이 존재해야 하는 필드명
    String[] required();

    //값이 존재해야 하는 필드명
    ConditionalMode mode() default AND;

    Class<? extends Payload>[] payload() default {};


    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        Conditional[] value();
    }
}
