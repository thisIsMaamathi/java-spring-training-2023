package cdw.springtraining.gatekeeper.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
/**
 * A custom annotation for validating phone numbers.
 * This annotation is used to mark fields or parameters that should be validated as phone numbers using the PhoneNumberValidator class.
 */
@Target({FIELD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
public @interface ValidPhoneNumber {
    public String message() default "Enter a valid indian phone number number";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
