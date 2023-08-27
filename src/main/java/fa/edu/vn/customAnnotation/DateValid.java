package fa.edu.vn.customAnnotation;

import fa.edu.vn.customAnnotation.impl.DateValidValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateValidValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)

public @interface DateValid {

    String message() default "Date is not valid, date must be after current date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
