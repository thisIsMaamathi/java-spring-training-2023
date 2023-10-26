package cdw.springtraining.gatekeeper.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
/**
 * A custom constraint validator for validating the format of phone numbers.
 */
public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, Long> {
    @Override
    public void initialize(ValidPhoneNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    /**
     * Validates the provided phone number.
     * @param phoneNumber
     * @param constraintValidatorContext the context for applying the constraint.
     * @return true if the phone number is valid, false otherwise.
     */
    @Override
    public boolean isValid(Long phoneNumber, ConstraintValidatorContext constraintValidatorContext) {
        String phone = String.valueOf(phoneNumber);

        if (phone.length() != 10) {
            return false;
        }

        return phone.matches("^[789][0-9]+$");
    }
}
