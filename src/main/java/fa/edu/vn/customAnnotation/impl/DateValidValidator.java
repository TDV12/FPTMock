package fa.edu.vn.customAnnotation.impl;

import fa.edu.vn.customAnnotation.DateValid;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DateValidValidator implements ConstraintValidator<DateValid, String> {

    @Override
    public void initialize(DateValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!ObjectUtils.isEmpty(value)) {
            LocalDate dateInput = LocalDate.parse(value);
            LocalDate currentDate = LocalDate.now();
            return dateInput.isAfter(currentDate) || dateInput.equals(currentDate);
        }
        return true;
    }
}
