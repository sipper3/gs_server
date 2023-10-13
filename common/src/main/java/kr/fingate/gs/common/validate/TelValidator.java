package kr.fingate.gs.common.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.logging.log4j.util.Strings;

public class TelValidator implements ConstraintValidator<Tel, String> {


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // null 및 빈값 체크는 다른 validator 사용
        if (value == null || value.equals(Strings.EMPTY)) {
            return true;
        }
        return value.matches("(01[016789]{1}|02|0[3-9]{1}[0-9]{1})(\\d{3,4})(\\d{4})");
    }
}
