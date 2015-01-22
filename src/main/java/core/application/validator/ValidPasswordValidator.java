package core.application.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import core.domain.model.IPasswordable;

public class ValidPasswordValidator implements
		ConstraintValidator<ValidPassword, IPasswordable> {

	@Override
	public void initialize(ValidPassword constraintAnnotation) {

	}

	@Override
	public boolean isValid(IPasswordable value,
			ConstraintValidatorContext context) {
		if (value.getRawPassword() != null && value.getRetypedPassword()!=null) {
			return (value.getRawPassword().equals(value.getRetypedPassword()));
		}
		return false;
	}

}
