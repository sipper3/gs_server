package kr.fingate.gs.common.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import kr.fingate.gs.common.util.ObjectUtil;
import org.springframework.beans.PropertyAccessorFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConditionalValidator implements ConstraintValidator<Conditional, Object> {
    private String selected;
    private String[] required;
    private String[] values;
    private String message;

    private ConditionalMode mode;

    @Override
    public void initialize(Conditional constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.selected = constraintAnnotation.selected();
        this.values = constraintAnnotation.values();
        this.required = constraintAnnotation.required();
        this.message = constraintAnnotation.message();
        this.mode = constraintAnnotation.mode();
    }

    /**
     *
     * @param object validate 대상 객체
     * @param context 제약조건이 평가되는 context
     * @return validation 성공 여부
     */
    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        boolean valid = true;
        boolean isCheck = false;
        Object actualValue = null;
        if(ObjectUtil.isEmpty(selected)) {
            isCheck = true;
        }else{
            actualValue = PropertyAccessorFactory
                    .forDirectFieldAccess(object)
                    .getPropertyValue(selected);
        }

        if (isCheck || actualValue != null) {
            Object selectedValue;
            if (actualValue instanceof Enum) {
                selectedValue = ((Enum<?>) actualValue).name();
            } else {
                selectedValue = actualValue;
            }

            if (isCheck || Arrays.asList(values).contains((String) selectedValue)) {
                if(mode == ConditionalMode.AND) {
                    List<String> fields = new ArrayList<>();
                    for (String fieldName : required) {
                        Object requiredValue = PropertyAccessorFactory
                                .forDirectFieldAccess(object)
                                .getPropertyValue(fieldName);

                        //요구되는 필드의 값이 빈 값이거나 null 인 경우 validation 은 실패
                        boolean empty = ObjectUtil.isEmpty(requiredValue);
                        if (empty) {
                            fields.add(fieldName);
                            break;
                        }
                    }
                    if(!fields.isEmpty()) {
                        valid = false;
                        fields.forEach(field -> {
                            context.disableDefaultConstraintViolation();
                            context
                                    .buildConstraintViolationWithTemplate(message)
                                    .addPropertyNode(field)
                                    .addConstraintViolation();
                        });
                    }
                }else if(mode == ConditionalMode.OR) {
                    boolean empty = true;
                    for (String fieldName : required) {
                        Object requiredValue = PropertyAccessorFactory
                                .forDirectFieldAccess(object)
                                .getPropertyValue(fieldName);

                        //요구되는 필드의 값이 빈 값이거나 null 인 경우 validation 은 실패
                        empty = empty && ObjectUtil.isEmpty(requiredValue);
                    }

                    valid = !empty;
                    if(!valid) {
                        message = "다음 항목 중 하나는 필수 입니다. ";
                        for (String fieldName : required) {
                            context.disableDefaultConstraintViolation();
                            context.buildConstraintViolationWithTemplate(message + Arrays.toString(required))
                                    .addPropertyNode(fieldName)
                                    .addConstraintViolation();
                        }
                    }
                }
            }
        }

        return valid;
    }
}
