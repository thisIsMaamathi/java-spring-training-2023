package cdw.springtraining.gatekeeper.validation;


import cdw.springtraining.gatekeeper.constant.CommonConstants;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

/**
 * A custom annotation for validating Aadhar numbers.
 * This annotation is used to mark fields or parameters that should be validated as Aadhar numbers using the AadharValidator class.
 */
@Target( { FIELD, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = AadharValidator.class)
public @interface ValidAadhar {
    public String message() default CommonConstants.ENTER_VALID_AADHAR;
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
