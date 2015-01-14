package xaw.rest_services.api.controller;

import java.util.ArrayList;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;

import xaw.rest_services.api.exception.NotValidArgumentException;
import xaw.rest_services.domain.validator.DTOValidationGroup;

public class RestValidator<T> {

	protected T o;
	protected ValidatorFactory factory;
	protected Validator validator;
	protected Set<ConstraintViolation<T>> constraintViolations;
	protected ArrayList<String> constraintViolationMessages;

	public RestValidator(T o) {
		this.o = o;
		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
		constraintViolations = validator.validate(o, Default.class,
				DTOValidationGroup.class);
	}
	
	public Boolean validateDTO() throws NotValidArgumentException {
		constraintViolationMessages = new ArrayList<>();
		if(constraintViolations!=null&&constraintViolations.size()>0){
			for(ConstraintViolation<T> constraintViolation:constraintViolations){
				constraintViolationMessages.add(constraintViolation.getMessage());
			}			
		}
		if(constraintViolationMessages!=null && constraintViolationMessages.size()>0){
			throw new NotValidArgumentException(constraintViolationMessages);
		}
		return true;
	}

}
