package pagos.payu.core.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateFormatValidator implements ConstraintValidator<ValidDateFormat, String>{
    @Override
    public void initialize(ValidDateFormat constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false;
        }

        // Verifica si el formato es "YYYY-MM"
        return value.matches("^\\d{4}-\\d{2}$");
    }
}
