package pagos.payu.core.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DateFormatValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDateFormat {

    String message() default "Formato de fecha inválido. Debe ser YYYY-MM";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}