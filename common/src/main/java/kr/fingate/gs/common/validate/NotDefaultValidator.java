package kr.fingate.gs.common.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import kr.fingate.gs.common.util.ObjectUtil;

public class NotDefaultValidator implements ConstraintValidator<NotDefault, Object> {


    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return !ObjectUtil.isEmpty(value);
    }
}
