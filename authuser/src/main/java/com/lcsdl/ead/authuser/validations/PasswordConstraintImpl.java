package com.lcsdl.ead.authuser.validations;

import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordConstraintImpl implements ConstraintValidator<PasswordConstraint, String>{
	
	private final static String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()\\-.[{}]:;',?/*~$^+=<>]).{6,20}$";

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		if(password == null || password.isBlank()) {
			return false;
		}
		
		Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
		
		if(!pattern.matcher(password).matches()) {
			return false;
		} else {
			return true;
		}
		
	}

	
}
