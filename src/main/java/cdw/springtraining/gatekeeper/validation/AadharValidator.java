package cdw.springtraining.gatekeeper.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
/**
 * A custom constraint validator for validating the format of Aadhar numbers.
 */
public class AadharValidator implements ConstraintValidator<ValidAadhar, Long> {
    @Override
    public void initialize(ValidAadhar constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    /**
     * Validates the provided Aadhar number.
     *
     * @param aadharNumber the Aadhar number
     * @param constraintValidatorContext the context for applying the constraint.
     * @return true if the Aadhar number is valid, false otherwise.
     */
    @Override
    public boolean isValid(Long aadharNumber, ConstraintValidatorContext constraintValidatorContext) {
        String aadhar = String.valueOf(aadharNumber);
        if (aadhar == null) {
            return false;
        }
        if (aadhar.length() != 12) {
            return false;
        }
        if (aadhar.startsWith("0") || aadhar.startsWith("1")) {
            return false;
        }
        return true;
    }
}
