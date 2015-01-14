package xaw.rest_services.application.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = ValidPasswordValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)

public @interface ValidPassword {
	String message() default "Not correct Password/Retype Password combination";

	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}